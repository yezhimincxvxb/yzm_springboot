package com.yzm.mapstruct.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
}
