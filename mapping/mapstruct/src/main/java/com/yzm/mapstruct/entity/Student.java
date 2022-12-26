package com.yzm.mapstruct.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer stuId;
    private String sName;
    private Date birthday;

    private User user;
}
