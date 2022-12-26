package com.yzm.valid.entity;

import com.yzm.valid.anno.order.First;
import com.yzm.valid.anno.order.Second;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ValidOrder {

    /*
    @NotBlank(groups = First.class)
    @Length(min = 2, max = 5, groups = Second.class)
    @Pattern(regexp = "^A\\d+$")
    private String message;
    */

    @NotBlank(groups = First.class)
    @Length(min = 2, max = 5)
    @Pattern(regexp = "^A\\d+$", groups = Second.class)
    private String message;

}
