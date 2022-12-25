package com.yzm.interceptor.anno;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
    boolean needAuth() default true;
}
