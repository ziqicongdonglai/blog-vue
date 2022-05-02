package com.zk.blog.api.service.impl;

import com.zk.blog.api.entity.User;
import com.zk.blog.api.mapper.UserMapper;
import com.zk.blog.api.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
