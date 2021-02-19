package com.mituh.vueblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mituh.vueblog.common.Result;
import com.mituh.vueblog.common.dto.LoginDto;
import com.mituh.vueblog.entity.User;
import com.mituh.vueblog.service.UserService;
import com.mituh.vueblog.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Hzz
 * @date 2020/11/26 14:29
 */

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    //@RequestBody 注解，接收前端传过来的json字符串中的数据，适合POST方式提交的数据
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            //System.out.println(SecureUtil.md5(loginDto.getPassword()));
            return new Result().fail("密码不正确");
        }
        //生成jwt   放在header中
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

        return new Result().ok(MapUtil.builder()
                .put("id",user.getId())
                .put("username",user.getUsername())
                .put("avatar",user.getAvatar())
                .put("email",user.getEmail())
                .map()
        );
    }

    //退出接口
    @RequiresAuthentication       //验证用户是否登录，等同于方法subject.isAuthenticated() 结果为true时。
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return new Result().ok(null);
    }

}
