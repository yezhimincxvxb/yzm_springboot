package com.yzm.hikari.service.impl;

import com.yzm.hikari.entity.User;
import com.yzm.hikari.mapper.UserMapper;
import com.yzm.hikari.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }
}
