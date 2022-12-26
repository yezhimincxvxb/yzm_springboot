package com.yzm.mapstruct.mapper;

import com.yzm.mapstruct.entity.Teacher;
import com.yzm.mapstruct.mapper.format.DateUtil;
import com.yzm.mapstruct.vo.TeacherVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DateUtil.class)
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "sex", source = "gender.desc")
//    @Mapping(target = "date", source = "date" , qualifiedBy = DateUtil.DateFormat.class)
    @Mapping(target = "date", source = "date" , qualifiedByName = "dateToStr")
    TeacherVo convert(Teacher teacher);
}
