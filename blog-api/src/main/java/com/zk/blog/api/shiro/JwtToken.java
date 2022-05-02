package com.zk.blog.api.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ke_zhang
 * @description 自定义一个JwtToken，来完成shiro的supports方法
 * @create 2022-05-02 16:01
 */
public class JwtToken implements AuthenticationToken {
    private final String token;
    public JwtToken(String jwt) {
        this.token = jwt;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}

