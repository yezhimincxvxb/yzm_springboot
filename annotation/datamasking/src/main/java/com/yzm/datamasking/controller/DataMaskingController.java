package com.yzm.datamasking.controller;

import com.yzm.datamasking.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mask")
public class DataMaskingController {

    @GetMapping("/user")
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setAge(20);
        user.setName("早知道");
        user.setPwd("123456");
        user.setPhone("136123456789");
        user.setIdentity("445281199910041272");
        System.out.println("user = " + user);
        return user;
    }
}
