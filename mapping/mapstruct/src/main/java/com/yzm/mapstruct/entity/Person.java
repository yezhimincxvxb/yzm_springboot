package com.yzm.mapstruct.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Person {

    private Long id;
    private String name;
    private Integer age;
    private float weight;
    private float height;
    private BigDecimal money;
    private String describe;
    private LocalDateTime createTime;
    private Date updateTime;

}
