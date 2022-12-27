package com.yzm.locker.controller;

import com.yzm.locker.anno.CurLockerAnno;
import com.yzm.locker.anno.CurLockerFieldAnno;
import com.yzm.locker.anno.CurLockerType;
import com.yzm.locker.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/demo01")
    @CurLockerAnno(lockPreName = "preKey", expireTime = 5 * 60 * 1000L, expressionType = CurLockerType.EL, expression = "#p0.userId")
    public void demo01(@RequestBody User user) {
        System.out.println("user = " + user);
    }

    @PostMapping("/demo02")
    @CurLockerAnno(lockPreName = "preKey")
    public void demo02(@RequestBody @CurLockerFieldAnno(field = {"userId", "username"}) User user) {
        System.out.println("user = " + user);
    }
}
