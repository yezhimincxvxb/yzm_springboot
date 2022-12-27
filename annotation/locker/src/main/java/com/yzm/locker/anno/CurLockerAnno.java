package com.yzm.locker.anno;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurLockerAnno {
    //锁的名称前缀
    String lockPreName();

    //锁过期时间（毫秒）默认3分钟
    long expireTime() default 1000L * 30;

    //表达式类型：默认普通、EL
    String expressionType() default CurLockerType.COMMON;

    //表达式
    String expression() default "";
}
