package com.yzm.serialize.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = -7026347252033134917L;
    @JSONField(ordinal = 1)
    private Integer id;
    @JSONField(ordinal = 2)
    private String home;
    @JSONField(ordinal = 3)
    private String school;
    @JSONField(ordinal = 4)
    private User user;
}
