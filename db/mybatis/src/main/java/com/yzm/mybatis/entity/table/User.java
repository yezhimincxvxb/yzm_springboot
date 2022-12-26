package com.yzm.mybatis.entity.table;

import com.yzm.mybatis.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private Integer id;
    private String username;
    private String realName;
    private String password;
    private String gender;
    private String remark;
    private Boolean state;
    private Boolean deleted;
    private Date createDate;
    private Date updateDate;

    private String noExistField;

}
