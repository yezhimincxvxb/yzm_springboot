package com.yzm.mapstruct.mapper;

import com.yzm.mapstruct.config.CentralConfig;
import com.yzm.mapstruct.entity.Student;
import com.yzm.mapstruct.entity.User;
import com.yzm.mapstruct.mapper.format.DateFormat;
import com.yzm.mapstruct.vo.StudentVo;
import com.yzm.mapstruct.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = CentralConfig.class, uses = DateFormat.class)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    // 嵌套
    @Mapping(target = "userVo", source = "user")
    StudentVo convert(Student student);

    // 使用自定义默认方法给内部引用属性做映射关系
    default UserVo byUser(User user) {
        if (user == null) return null;

        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getName());
        userVo.setCreateTime(user.getCreateTime());
        return userVo;
    }

    // 或者申明映射
//    @Mapping(target = "username", source = "name")
//    UserVo convert(User user);
}
