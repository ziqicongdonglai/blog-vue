package com.zk.blog.api.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ke_zhang
 * @description 登录成功用户信息载体
 * @create 2022-05-02 16:06
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
    private String email;
}
