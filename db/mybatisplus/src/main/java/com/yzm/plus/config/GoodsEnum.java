package com.yzm.plus.config;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum GoodsEnum {

    DOWN(0, "下架"),
    UP(1, "上架"),
    HOT(2, "热销");

    GoodsEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 标记数据库存的值
     */
    @EnumValue
    private int code;
    /**
     * 标记响应json值
     */
    @JsonValue
    private String desc;

    @Override
    public String toString() {
        return this.desc;
    }
}
