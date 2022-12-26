package com.yzm.mybatis.entity.vo;

import com.yzm.mybatis.entity.base.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo extends BaseVo {

    private static final long serialVersionUID = 2062504472352642553L;
    private Integer id;
    private String username;
    private String realName;
    private String password;
    private String gender;
    private String remark;
    private boolean state;
    private boolean deleted;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
