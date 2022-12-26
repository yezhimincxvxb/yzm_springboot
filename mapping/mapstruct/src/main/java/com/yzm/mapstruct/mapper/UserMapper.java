package com.yzm.mapstruct.mapper;

import com.yzm.mapstruct.entity.User;
import com.yzm.mapstruct.vo.UserVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "name")
    UserVo convert(User user);

    @Mapping(target = "username", source = "name")
    void convert2(User user, @MappingTarget UserVo userVo);

    @InheritConfiguration(name = "convert")
        // 继承映射关系
    UserVo convert3(User user);

    // 逆向映射
    @InheritInverseConfiguration(name = "convert")
    // 继承逆配置
    User convert4(UserVo userVo);
}
