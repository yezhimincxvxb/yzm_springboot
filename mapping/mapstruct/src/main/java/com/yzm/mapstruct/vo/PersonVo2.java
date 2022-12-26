package com.yzm.mapstruct.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PersonVo2 {

    private Long id;
    private String username;
    private String money;
    private String weight;
    private String height;
    private String describe;
    private String createTime;
    private String updateTime;
    private Date date;
}
