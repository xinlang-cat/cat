package com.xinlang.zly_xyx.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    /**
     * 记录普通参数的类型和序列化的对象
     * @return
     */
   boolean recordParam() default  true;
}
