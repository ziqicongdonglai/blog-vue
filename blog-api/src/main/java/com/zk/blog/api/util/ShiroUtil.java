package com.zk.blog.api.util;

import com.zk.blog.api.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author ke_zhang
 * @description ShiroUtil⼯具类
 * @create 2022-05-02 16:00
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile)
                SecurityUtils.getSubject().getPrincipal();
    }
}

