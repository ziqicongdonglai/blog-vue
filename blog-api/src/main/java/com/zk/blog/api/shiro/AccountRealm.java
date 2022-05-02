package com.zk.blog.api.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.zk.blog.api.entity.User;
import com.zk.blog.api.service.UserService;
import com.zk.blog.api.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ke_zhang
 * @description realm负责认证与授权
 * @create 2022-05-02 16:07
 */
@Component
public class AccountRealm extends AuthorizingRealm {
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo
    doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtil.getClaimByToken((String)
                jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);
        return new SimpleAuthenticationInfo(profile,
                jwtToken.getCredentials(), getName());
    }
}

