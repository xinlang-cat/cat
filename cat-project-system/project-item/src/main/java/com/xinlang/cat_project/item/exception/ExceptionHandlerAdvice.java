package com.xinlang.cat_project.item.exception;

import com.xinlang.cat_project.item.VO.ExceptionResult;
import com.xinlang.cat_project.item.enums.ExceptionEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 梁应昌
 * 2019/9/27
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ExceptionResult> handleException(ItemException e){
        ExceptionEnum em = e.getExceptionEnum();
        return ResponseEntity.status(e.getExceptionEnum().getCode()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
