package com.zk.blog.api.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ke_zhang
 * @description 响应结果封装类：暂时⽤0表示成功，-1表示失败
 * @create 2022-05-02 15:48
 */
@Data
public class ResponseResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static ResponseResult success(Object data) {
        ResponseResult m = new ResponseResult();
        m.setCode(200);
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static ResponseResult success(String mess, Object data) {
        ResponseResult m = new ResponseResult();
        m.setCode(200);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static ResponseResult fail(String mess) {
        ResponseResult m = new ResponseResult();
        m.setCode(401);
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static ResponseResult fail(String mess, Object data) {
        ResponseResult m = new ResponseResult();
        m.setCode(401);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
}
