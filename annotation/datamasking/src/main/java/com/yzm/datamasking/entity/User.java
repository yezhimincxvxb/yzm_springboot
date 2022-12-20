package com.yzm.datamasking.entity;

import com.yzm.datamasking.config.DataMasking;
import com.yzm.datamasking.config.DataMaskingFunc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7186264404325220397L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    @DataMasking(maskFunc = DataMaskingFunc.NAME_MASK, maskChar = "$")
    private String name;

    /**
     * 密码
     */
    @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK, maskChar = "#")
    private String pwd;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    @DataMasking(maskFunc = DataMaskingFunc.PHONE_MASK)
    private String phone;

    /**
     * 身份号码
     */
    @DataMasking(maskFunc = DataMaskingFunc.IDENTITY_MASK)
    private String identity;

}
