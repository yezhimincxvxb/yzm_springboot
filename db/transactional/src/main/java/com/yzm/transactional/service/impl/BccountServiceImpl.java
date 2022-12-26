package com.yzm.transactional.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzm.transactional.entity.Account;
import com.yzm.transactional.mapper.AccountMapper;
import com.yzm.transactional.service.BccountService;
import com.yzm.transactional.service.CccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author Yzm
 * @since 2022-03-09
 */
@Service
public class BccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements BccountService {

    private final CccountService cccountService;

    public BccountServiceImpl(CccountService cccountService) {
        this.cccountService = cccountService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void methodB1() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 100);
        updateById(xming);

        int i = 1 / 0;

        // 嵌套C方法
        cccountService.methodC1();
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void methodB12() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 100);
        updateById(xming);

        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void methodB2() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 200);
        updateById(xming);

//        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY, rollbackFor = Exception.class)
    public void methodB3() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 200);
        updateById(xming);

        int i = 1 / 0;
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void methodB4() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 200);
        updateById(xming);

        cccountService.methodC4();
        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void methodB5() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 200);
        updateById(xming);

        int i = 1 / 0;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER, rollbackFor = Exception.class)
    public void methodB6() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 200);
        updateById(xming);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void methodB7() {
        Account xming = getById(2);
        xming.setMoney(xming.getMoney() + 200);
        updateById(xming);

        cccountService.methodC7();

//        int i = 1 / 0;
    }

}
