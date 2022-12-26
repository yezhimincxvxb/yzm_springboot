package com.yzm.mapstruct.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVo {
    private Integer id;
    private String username;
    private LocalDateTime createTime;
}
