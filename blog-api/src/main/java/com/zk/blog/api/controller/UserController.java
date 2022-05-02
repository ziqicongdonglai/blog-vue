package com.zk.blog.api.controller;


import com.zk.blog.api.entity.User;
import com.zk.blog.api.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangke
 * @since 2022-05-02
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    /**
     * 测试实体校验
     *
     * @param user 入参
     * @return Object
     */
    @PostMapping("/save")
    public Object testUser(@RequestBody @Validated User user) {
        return user.toString();
    }
}
