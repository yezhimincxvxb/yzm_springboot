package com.yzm.mapstruct.entity;

import lombok.Getter;

@Getter
public enum SexEnum {

    MAN(1, "男"),
    WOMAN(0, "女");

    int sex;
    String desc;

    SexEnum(int sex, String desc) {
        this.sex = sex;
        this.desc = desc;
    }
}
