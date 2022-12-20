package com.yzm.druid.service.impl;

import com.yzm.druid.entity.User;
import com.yzm.druid.mapper.UserMapper;
import com.yzm.druid.service.UserService;
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
