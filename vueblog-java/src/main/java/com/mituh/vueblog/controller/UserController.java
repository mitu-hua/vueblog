package com.mituh.vueblog.controller;


import com.mituh.vueblog.common.Result;
import com.mituh.vueblog.entity.User;
import com.mituh.vueblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hzz
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public Result getUser(){
        User user = userService.getById(1);
        return new Result().ok(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return new Result().ok(user);
    }
}
