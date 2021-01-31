package com.mituh.vueblog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mituh.vueblog.common.Result;
import com.mituh.vueblog.entity.Blog;
import com.mituh.vueblog.service.BlogService;
import com.mituh.vueblog.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hzz
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){  //currentPage为分页的当前页码
        Page page = new Page(currentPage,1);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));//根据创建时间降序排列
        return new Result().ok(pageData);
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        //判断文章是否为空
        /*if(blog ==null){
            return new Result().fail("该文章不存在或已删除");
        }*/
        //断言判断  处理异常
        Assert.notNull(blog,"该博客不存在或已被删除");
        return new Result().ok(blog);
    }

    /**
     *
     * @param blog
     * @return
     * 注意：@RequiresAuthentication表示需要登录之后才能访问接口
     * 这里@Validated先验证，之后@RequiresAuthentication才会验证
     */
    @RequiresAuthentication
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody Blog blog){

        Blog temp = null;
        Long userLoginId = ShiroUtils.getProfile().getId();
        if(blog.getId() != null){
            //如果id不为空，则修改
            temp = blogService.getById(blog.getId());
            //只能编辑自己的文章，判断userId是否和当前登录账户的id相同
            //使用断言判断
            Assert.isTrue(temp.getUserId().equals(userLoginId),"没有权限编辑当前文章");

        }else{
            //如果id为空，则新增
            temp = new Blog();
            temp.setUserId(userLoginId);
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return new Result(200,"操作成功",null);
    }
}
