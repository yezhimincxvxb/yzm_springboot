package com.yzm.jackson.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"name","age","id"})
@JsonRootName(value = "user")
public class Demo02 {

    private Long id;
    private String name;
    private Integer age;

    private String content;
    @JsonRawValue
    private String jsonContent;
}

