package com.yzm.mapstruct.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Teacher {

    private Integer id;
    private String name;
    private SexEnum gender;
    private Date date;

}
