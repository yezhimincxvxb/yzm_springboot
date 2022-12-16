package com.yzm;

import com.yzm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBoot01Test {

    @Autowired
    private UserService userService;

    @Test
    public void hello() {
        System.out.println(userService.hello());
    }
}
