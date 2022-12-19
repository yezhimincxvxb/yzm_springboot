package com.yzm.mybatis.service;

import com.yzm.mybatis.entity.base.Page;
import com.yzm.mybatis.entity.table.User;
import com.yzm.mybatis.entity.vo.UserVo;
import com.yzm.mybatis.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<User, UserVo> {

    List<User> findAll();

    List<User> getByCondition(String column, String value);

    Page<User> pageUser(Page page, User user);
}
