package com.xinlang.yx.project_record.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/19
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> errReqException(IllegalArgumentException e){
        Map<String,Object> map = new HashMap<>();
        map.put("code",HttpStatus.BAD_REQUEST.value());
        map.put("message",e.getMessage());
        return map;
    }
}
