package com.yzm.controller;

import com.yzm.entity.User;
import com.yzm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public void hello() {
        logger.trace("HelloController...");
        logger.debug("HelloController...");
        logger.info("HelloController...");
        logger.warn("HelloController...");
        logger.error("HelloController...");
        userService.hello();
    }

    @GetMapping("/listUser")
    public void listUser() {
        List<User> users = userService.listUser();
        users.forEach(System.out::println);
    }
}
