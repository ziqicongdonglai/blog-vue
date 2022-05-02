package com.zk.blog.api.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author ke_zhang
 * @description 登陆Dto类
 * @create 2022-05-02 21:21
 */
@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
