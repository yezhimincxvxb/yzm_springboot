package com.yzm.mybatis.mapper;

import com.yzm.mybatis.entity.base.Page;
import com.yzm.mybatis.entity.table.User;
import com.yzm.mybatis.entity.vo.UserVo;
import com.yzm.mybatis.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User, UserVo> {

    @Select("select id, username, real_name as realName, password from user")
    List<User> findAll();

    @Select("select username, real_name as realName, password, remark from user where ${column} = #{value}")
    List<User> getByCondition(@Param("column") String column, @Param("value") String value);

    List<User> pageUser(Page page, @Param("user") User user);

}
