package com.yzm.locker.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -7075688542022280658L;

    private Integer userId;
    private String username;
    private String password;
    private Double height;
    private Double weight;
    private LocalDate birthday;
    private LocalDateTime createDate;

}
