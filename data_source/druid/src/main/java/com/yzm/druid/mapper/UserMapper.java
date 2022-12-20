package com.yzm.druid.mapper;

import com.yzm.druid.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from `user`")
    List<User> listUser();

}
