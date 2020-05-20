package com.xinlang.zly_xyx.cat_inform.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.EOFException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张龙毅 18777811286@163.com
 * 2019/7/26
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> badRequstException(IllegalArgumentException e){
        Map<String,Object> map = new HashMap<>();
        map.put("code",HttpStatus.BAD_REQUEST.value());
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler({EOFException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> badEOFException(EOFException e){
        Map<String,Object> map = new HashMap<>();
        map.put("message","这是正常的，一个客户端断开了连接！！");
        return map;
    }
}
