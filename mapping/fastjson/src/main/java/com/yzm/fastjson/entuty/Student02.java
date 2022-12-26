package com.yzm.fastjson.entuty;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JSONType(includes = {"id", "age"})
public class Student02 {

    private Integer id;
    @JSONField
    private String name;
    private Integer age;

}
