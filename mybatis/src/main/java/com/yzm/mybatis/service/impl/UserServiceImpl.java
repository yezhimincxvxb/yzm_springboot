package com.yzm.mybatis.service.impl;

import com.yzm.mybatis.entity.base.Page;
import com.yzm.mybatis.entity.table.User;
import com.yzm.mybatis.entity.vo.UserVo;
import com.yzm.mybatis.mapper.UserMapper;
import com.yzm.mybatis.service.UserService;
import com.yzm.mybatis.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserVo> implements UserService {

    private UserMapper userMapper = null;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
        super.setMapper(userMapper);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> getByCondition(String column, String value) {
        return userMapper.getByCondition(column, value);
    }

    @Override
    public Page<User> pageUser(Page page, User user) {
        List<User> users = userMapper.pageUser(page, user);
        page.setResult(users);
        return page;
    }
}
