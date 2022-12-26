package com.yzm.fastjson.entuty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private Integer age;

}
