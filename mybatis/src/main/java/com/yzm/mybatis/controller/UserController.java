package com.yzm.mybatis.controller;

import com.yzm.mybatis.entity.base.Page;
import com.yzm.mybatis.entity.table.User;
import com.yzm.mybatis.entity.vo.UserVo;
import com.yzm.mybatis.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getOne")
    public User getOne() {
        return userService.getOne(2);
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/getByCondition")
    public List<User> getByCondition(String column, String value) {
        return userService.getByCondition(column, value);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping("/page")
    public Page<User> page() {
        UserVo build = UserVo.builder().build();
        build.setPage(new Page<>(2, 3));
        return userService.page(build);
    }

    @GetMapping("/pageUser")
    public Page<User> pageUser() {
        return userService.pageUser(new Page<>(1, 3), new User());
    }

}
