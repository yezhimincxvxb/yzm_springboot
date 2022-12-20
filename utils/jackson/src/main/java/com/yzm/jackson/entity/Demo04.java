package com.yzm.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yzm.jackson.config.CustomDateDeserializer;
import com.yzm.jackson.config.CustomDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demo04 {

    @JsonAlias({ "fName", "f_name" })
    private String firstName;
    private String lastName;

}

