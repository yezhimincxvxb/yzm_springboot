package com.yzm.jackson.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yzm.jackson.entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;

@RestController
public class DemoController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/demo01")
    public void demo01() throws JsonProcessingException {
        Demo01 demo01 = new Demo01();
        demo01.setTheName("张三");
        demo01.add("key1","val1");
        demo01.add("key2","val2");

        System.out.println("序列化：");
        String result = objectMapper.writeValueAsString(demo01);
        System.out.println(result);

        System.out.println("反序列化：");
        Demo01 readValue = objectMapper.readValue(result, Demo01.class);
        System.out.println(readValue);
    }

    @GetMapping("/demo02")
    public void demo02() throws JsonProcessingException {
        Demo02 demo02 = Demo02.builder().id(100L).name("张三").age(20).content("{\"key\":\"val\"}").jsonContent("{\"key\":\"val\"}").build();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = objectMapper.writeValueAsString(demo02);
        System.out.println(result);
    }

    @GetMapping("/demo03")
    public void demo03() throws JsonProcessingException {
        Demo03 demo03 = Demo03.builder().id(100L).name("张三").age(20).date(new Date()).build();

        System.out.println("序列化：");
        String result = objectMapper.writeValueAsString(demo03);
        System.out.println(result);

        System.out.println("反序列化：");
        Demo03 readValue = objectMapper.readValue(result, Demo03.class);
        System.out.println(readValue);
    }

    @GetMapping("/demo04")
    public void demo04() throws JsonProcessingException {
        String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";
        Demo04 readValue = objectMapper.readValue(json, Demo04.class);
        System.out.println(readValue);
    }

    @GetMapping("/demo05")
    public void demo05() throws JsonProcessingException {
        Demo05 demo05 = Demo05.builder().id(100L).name("张三").age(20).a("A").b("B").c("C").build();
        String result = objectMapper.writeValueAsString(demo05);
        System.out.println(result);
    }

    @GetMapping("/demo06")
    public void demo06() throws JsonProcessingException {
        Demo06 demo06 = Demo06.builder().id(100L).name("").age(0).build();
        String result = objectMapper.writeValueAsString(demo06);
        System.out.println(result);
    }

    @GetMapping("/demo07")
    public void demo07() throws JsonProcessingException {
        Demo07.Name name = new Demo07.Name("John", "Doe");
        Demo07 demo07 = Demo07.builder().id(100L).name(name).date(new Date()).build();
        String result = objectMapper.writeValueAsString(demo07);
        System.out.println(result);
    }
}
