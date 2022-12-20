package com.yzm.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"a","b","c"})
public class Demo05 {

    @JsonIgnore
    private Long id;

    private String name;
    private Integer age;

    private String a;
    private String b;
    private String c;
}

