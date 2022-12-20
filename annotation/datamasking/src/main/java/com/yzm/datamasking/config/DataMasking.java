package com.yzm.datamasking.config;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@JacksonAnnotationsInside //标识该注解属于Jackson
@JsonSerialize(using = DataMaskingSerializer.class) //序列化类
//@JsonDeserialize 反序列化
public @interface DataMasking {

    DataMaskingFunc maskFunc() default DataMaskingFunc.NONE;

    String maskChar() default "*";
}
