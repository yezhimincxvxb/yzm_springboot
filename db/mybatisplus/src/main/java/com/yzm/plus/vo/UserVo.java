package com.yzm.plus.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class UserVo {

    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("性别[0-女，1-男]")
    private String gender;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态[0-冻结，1-正常]")
    private Boolean state;

    @ApiModelProperty("删除状态[0-未删除，1-已删除]")
    private Boolean deleted;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("更新时间(时间戳)")
    private String updateDate;


}
