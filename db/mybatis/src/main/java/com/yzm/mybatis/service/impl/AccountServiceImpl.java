package com.yzm.mybatis.service.impl;

import com.yzm.mybatis.entity.table.Account;
import com.yzm.mybatis.entity.vo.AccountVo;
import com.yzm.mybatis.mapper.AccountMapper;
import com.yzm.mybatis.service.AccountService;
import com.yzm.mybatis.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountVo> implements AccountService {

    private AccountMapper accountMapper = null;

    @Autowired
    private void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
        super.setMapper(accountMapper);
    }

}
