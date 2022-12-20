package com.yzm.aop.controller;

import com.yzm.aop.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

    @Autowired
    private AopService aopService;

    @GetMapping("/add")
    public String addUser() {
        return aopService.add();
    }

    @GetMapping("/update")
    public String updateUser() {
        return aopService.update(1L);
    }

    @GetMapping("/select")
    public String selectUser() {
        return aopService.select();
    }

    @GetMapping("/delete")
    public String deleteUser() {
        return aopService.delete(1L);
    }

    @GetMapping("/getById")
    public String getById() {
        return aopService.getById();
    }
}
