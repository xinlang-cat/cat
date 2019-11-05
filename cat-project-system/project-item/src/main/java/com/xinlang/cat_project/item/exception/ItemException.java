package com.xinlang.cat_project.item.exception;

import com.xinlang.cat_project.item.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 梁应昌
 * 2019/9/27
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
