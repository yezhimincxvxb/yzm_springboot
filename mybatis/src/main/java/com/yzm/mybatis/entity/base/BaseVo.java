package com.yzm.mybatis.entity.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVo implements Serializable {
    private static final long serialVersionUID = -5942940566855823712L;
    private Page page = new Page<>();
}
