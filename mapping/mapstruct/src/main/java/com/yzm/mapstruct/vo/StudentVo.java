package com.yzm.mapstruct.vo;

import lombok.Data;

@Data
public class StudentVo {
    private Integer stuId;
    private String sName;
    private String grade;

    private String birthday;

    private UserVo userVo;
}
