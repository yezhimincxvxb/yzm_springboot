package com.yzm.mybatis.controller;

import com.yzm.mybatis.entity.base.Page;
import com.yzm.mybatis.entity.table.Account;
import com.yzm.mybatis.entity.vo.AccountVo;
import com.yzm.mybatis.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getOne")
    public Account getOne() {
        return accountService.getOne(2);
    }

    @GetMapping("/list")
    public List<Account> list() {
        return accountService.list();
    }

    @GetMapping("/page")
    public Page<Account> page() {
        AccountVo build = AccountVo.builder().build();
        build.setPage(new Page<>(2, 3));
        return accountService.page(build);
    }

}
