package com.zk.blog.api.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.blog.api.common.ResponseResult;
import com.zk.blog.api.entity.Blog;
import com.zk.blog.api.service.BlogService;
import com.zk.blog.api.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangke
 * @since 2022-05-02
 */
@RestController
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("/blogs")
    public ResponseResult blogs(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>
                ().orderByDesc("created"));
        return ResponseResult.success(pageData);
    }

    @GetMapping("/blog/{id}")
    public ResponseResult detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return ResponseResult.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public ResponseResult edit(@Validated @RequestBody Blog blog) {
        System.out.println(blog.toString());
        Blog temp;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(Objects.equals(temp.getUserId(),
                    ShiroUtil.getProfile().getId()), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(new Date());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created",
                "status");
        blogService.saveOrUpdate(temp);
        return ResponseResult.success("操作成功", null);
    }
}
