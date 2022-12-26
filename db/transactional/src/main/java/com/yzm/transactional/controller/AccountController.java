package com.yzm.transactional.controller;


import com.yzm.transactional.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author Yzm
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/test01")
    public void test01() {
        accountService.methodA1();
    }


    @GetMapping("/test02")
    public void test02() {
        accountService.methodA2();
    }

    @GetMapping("/test03")
    public void test03() {
        accountService.methodA3();
    }


    @GetMapping("/test04")
    public void test04() {
        accountService.methodA4();
    }

    @GetMapping("/test05")
    public void test05() {
        accountService.methodA5();
    }


    @GetMapping("/test06")
    public void test06() {
        accountService.methodA6();
    }

    @GetMapping("/test07")
    public void test07() {
        accountService.methodA7();
    }


    @GetMapping("/test08")
    public void test08() {
        accountService.methodA7();
    }

}
