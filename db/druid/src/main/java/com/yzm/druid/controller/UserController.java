package com.yzm.druid.controller;

import com.yzm.druid.entity.User;
import com.yzm.druid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listUser")
    public List<User> listUser() {
        return userService.listUser();
    }
}
