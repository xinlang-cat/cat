package com.xinlang.zly_xyx.cat_user.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/23
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> badReqException(IllegalArgumentException e){
        Map<String,Object> map  =  new HashMap<>();
        map.put("code",HttpStatus.BAD_REQUEST.value());
        map.put("message",e.getMessage());
        return map;
    }
}
