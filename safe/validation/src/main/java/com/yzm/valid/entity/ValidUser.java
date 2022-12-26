package com.yzm.valid.entity;

import com.yzm.valid.anno.IsPhone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ValidUser {

    @NotNull
    private Integer id;

    @NotBlank(message = "姓名不为空")
    @Length(min = 2, max = 5, message = "姓名长度为2~5个汉字")
    private String name;

    @Size(min = 1, max = 3, message = "爱好个数为1~3个")
    private String[] hobby;

    //深入检验对象属性
    @Valid
    private Dog dog;

    @IsPhone
    private String phone;
}

