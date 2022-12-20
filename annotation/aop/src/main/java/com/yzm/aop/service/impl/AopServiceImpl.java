package com.yzm.aop.service.impl;

import com.yzm.aop.config.A;
import com.yzm.aop.service.AopService;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {
    @Override
    public String add() {
        return "添加";
    }

    @Override
    public String update(Long id) {
        return "修改" + id;
    }

    @Override
    public String select() {
        int i = 1 / 0;
        return "查询";
    }

    @Override
    public String delete(Long id) {
        return "删除" + id;
    }

    @Override
    @A
    public String getById() {
        return "注解使用";
    }

}
