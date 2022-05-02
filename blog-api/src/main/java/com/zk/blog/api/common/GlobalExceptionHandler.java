package com.zk.blog.api.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ke_zhang
 * @description 全局统⼀异常处理类
 * @create 2022-05-02 20:09
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResponseResult handler(ShiroException e) {
        log.error("运⾏时异常：----------------${e}");
        return ResponseResult.fail(e.getMessage(), null);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------------${e}");
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError =
                bindingResult.getAllErrors().stream().findFirst().get();
        return ResponseResult.fail(objectError.getDefaultMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseResult handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------${e}");
        return ResponseResult.fail(e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseResult handler(RuntimeException e) {
        log.error("运⾏时异常：----------------${e}");
        return ResponseResult.fail(e.getMessage());
    }
}
