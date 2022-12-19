package com.yzm.log4j2.service.impl;

import com.yzm.log4j2.entity.User;
import com.yzm.log4j2.mapper.UserMapper;
import com.yzm.log4j2.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Log logger = LogFactory.getLog(getClass());

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
