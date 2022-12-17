package com.yzm.logback.service.impl;

import com.yzm.logback.entity.User;
import com.yzm.logback.mapper.UserMapper;
import com.yzm.logback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public void hello() {
        logger.trace("UserServiceImpl...");
        logger.debug("UserServiceImpl...");
        logger.info("UserServiceImpl...");
        logger.warn("UserServiceImpl...");
        logger.error("UserServiceImpl...");
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }
}
