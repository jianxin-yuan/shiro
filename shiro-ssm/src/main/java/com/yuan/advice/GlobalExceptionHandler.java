package com.yuan.advice;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuan
 * @date 2019/10/19 7:46 下午
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public Object unauthorizedHandler(){
        Map<String, String> map = new HashMap<>();
        map.put("code", "403");
        map.put("message", "unauthorized");
        return map;
    }
}
