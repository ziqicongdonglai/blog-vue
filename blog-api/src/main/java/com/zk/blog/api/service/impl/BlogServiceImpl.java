package com.zk.blog.api.service.impl;

import com.zk.blog.api.entity.Blog;
import com.zk.blog.api.mapper.BlogMapper;
import com.zk.blog.api.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangke
 * @since 2022-05-02
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
