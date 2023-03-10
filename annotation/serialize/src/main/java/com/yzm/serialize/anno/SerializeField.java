package com.yzm.serialize.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializeField {

    Class<?> clazz();

    /**
     * 需要返回的字段
     */
    String[] includes() default {};

    /**
     * 需要去除的字段
     */
    String[] excludes() default {};
}
