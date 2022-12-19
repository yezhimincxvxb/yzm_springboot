package com.yzm.objectmappper.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzm.objectmappper.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class UserController {

    @Resource(name = "myObjectMapper")
    private ObjectMapper objectMapper;

    private static User user1;
    private static User user2;

    static {
        user1 = User.builder().id(100L).name("abc").age(20).money(new BigDecimal("998")).createTime(new Date()).updateTime(LocalDateTime.now()).build();
        user2 = User.builder().id(200L).name("ABC").age(25).money(new BigDecimal("1998")).createTime(new Date()).updateTime(LocalDateTime.now()).build();
    }

    /**
     * 对象 <==> JSON字符串
     */
    @GetMapping("/demo01")
    public void demo01() {
        try {
            String s = objectMapper.writeValueAsString(user1);
            System.out.println("s = " + s);

            User user = objectMapper.readValue(s, User.class);
            System.out.println("对象 = " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 集合对象 <==> JSON字符串
     */
    @GetMapping("/demo02")
    public void demo02() {
        try {
            List<User> list = Arrays.asList(user1, user2);
            String s = objectMapper.writeValueAsString(list);
            System.out.println("s = " + s);

            // constructArrayType、constructParametricType
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, User.class);
            List<User> users = objectMapper.readValue(s, javaType);
            System.out.println("List = " + users);

            List<User> users2 = objectMapper.readValue(s, new TypeReference<List<User>>() {
            });
            System.out.println("List2 = " + users2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Map对象 <==> JSON字符串
     */
    @GetMapping("/demo03")
    public void demo03() {
        try {
            Map<String, User> map = new HashMap<>();
            map.put("user1", user1);
            map.put("user2", user2);

            String s = objectMapper.writeValueAsString(map);
            System.out.println("s = " + s);

            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, User.class);
            Map<String, User> mapUser = objectMapper.readValue(s, javaType);
            System.out.println("Map = " + mapUser);

            Map<String, User> mapUser2 = objectMapper.readValue(s, new TypeReference<Map<String, User>>() {
            });
            System.out.println("Map2 = " + mapUser2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
