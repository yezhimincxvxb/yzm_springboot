package com.yzm.serialize.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -5537772229918482024L;

    @JSONField(ordinal = 1)
    private Long id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String password;
    @JSONField(ordinal = 4)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;
    @JSONField(ordinal = 5)
    private Dog dog;
    @JSONField(ordinal = 6)
    private Address address;


    @JSONField(ordinal = 7)
    private List<Address> addresses;

    public User(Long id, String name, String password, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.time = time;
    }
}



