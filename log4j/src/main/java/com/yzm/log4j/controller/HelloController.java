package com.yzm.log4j.controller;

import com.yzm.log4j.entity.User;
import com.yzm.log4j.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final Log log = LogFactory.getLog(getClass());

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
