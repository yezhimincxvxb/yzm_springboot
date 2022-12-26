package com.yzm.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

public class Demo01 {

    private String name;

    private Map<String, String> map = new HashMap<>();

    @JsonGetter("name")
    public String getTheName() {
        return name;
    }

    @JsonSetter("name")
    public void setTheName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getMap() {
        return map;
    }

    @JsonAnySetter
    public void add(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String toString() {
        return "Demo01{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}

