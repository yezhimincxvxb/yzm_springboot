package com.yzm.hikari.mapper;

import com.yzm.hikari.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from `user`")
    List<User> listUser();

}
