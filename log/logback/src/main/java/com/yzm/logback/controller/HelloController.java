package com.yzm.logback.controller;

import com.yzm.logback.entity.User;
import com.yzm.logback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public void hello() {
        log.trace("HelloController...");
        log.debug("HelloController...");
        log.info("HelloController...");
        log.warn("HelloController...");
        log.error("HelloController...");
        userService.hello();
    }

    @GetMapping("/listUser")
    public void listUser() {
        List<User> users = userService.listUser();
        users.forEach(System.out::println);
    }
}
