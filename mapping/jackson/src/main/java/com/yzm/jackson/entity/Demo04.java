package com.yzm.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Demo04 {

    @JsonAlias({ "fName", "f_name" })
    private String firstName;
    @JsonProperty("lName")
    private String lastName;

    private String la;

    private String last;

    @JsonProperty("la")
    public String getTheLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public String getLast() {
        return last;
    }

    @JsonProperty("last")
    public void setTheLast(String last) {
        this.last = last;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

