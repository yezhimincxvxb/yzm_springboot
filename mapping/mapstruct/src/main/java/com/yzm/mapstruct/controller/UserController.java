package com.yzm.mapstruct.controller;

import com.yzm.mapstruct.entity.Student;
import com.yzm.mapstruct.entity.User;
import com.yzm.mapstruct.mapper.StudentMapper;
import com.yzm.mapstruct.mapper.UserMapper;
import com.yzm.mapstruct.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    static User user;

    static {
        user = new User();
        user.setId(123);
        user.setName("王武");
        user.setCreateTime(LocalDateTime.now());
    }

    @GetMapping("demo01")
    public void demo01() {
        System.out.println(user);
        System.out.println(UserMapper.INSTANCE.convert(user));
    }

    @GetMapping("demo02")
    public void demo02() {
        System.out.println(user);
        user.setId(null);
        UserVo userVo = new UserVo();
        userVo.setUsername("李依伊");
        userVo.setId(333);
        UserMapper.INSTANCE.convert2(user, userVo);
        System.out.println(userVo);
    }

    @GetMapping("demo03")
    public void demo03() {
        System.out.println(user);
        System.out.println(UserMapper.INSTANCE.convert3(user));
    }

    @GetMapping("demo04")
    public void demo04() {
        UserVo userVo = new UserVo();
        userVo.setId(444);
        userVo.setUsername("王武6");
        userVo.setCreateTime(LocalDateTime.now());
        System.out.println(UserMapper.INSTANCE.convert4(userVo));
    }

    @GetMapping("demo05")
    public void demo05() {
        Student student = new Student();
        student.setStuId(55);
        student.setSName("小米");
        student.setBirthday(new Date());
        student.setUser(user);
        System.out.println(StudentMapper.INSTANCE.convert(student));
    }
}
