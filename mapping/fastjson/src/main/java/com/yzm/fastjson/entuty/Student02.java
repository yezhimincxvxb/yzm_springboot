package com.yzm.fastjson.entuty;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.yzm.fastjson.using.BigDecimalSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JSONType(includes = {"id", "name", "decimal"})
public class Student02 {

    private Integer id;
    private String name;
    private Integer age;
    @JSONField(serializeUsing = BigDecimalSerialize.class)
    private BigDecimal decimal;

}
