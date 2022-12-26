package com.yzm.mapstruct.mapper;

import com.yzm.mapstruct.entity.Person;
import com.yzm.mapstruct.entity.User;
import com.yzm.mapstruct.vo.PersonVo;
import com.yzm.mapstruct.vo.PersonVo2;
import com.yzm.mapstruct.vo.PersonVo3;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    // 字段完全一致
    PersonVo convert(Person person);

    /**
     * 忽略id
     * 字段名称不一致
     * 字段类型不一致
     * 对应字段为null，则使用默认值
     * 数字格式化
     * 时间格式化
     * 表达式
     */
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "username", source = "name"),
            @Mapping(target = "weight", source = "weight"),
            @Mapping(target = "height", source = "height"),
            @Mapping(target = "describe", source = "describe", defaultValue = "默认值"),
            @Mapping(target = "money", numberFormat = "#.00"),
            @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy/MM/dd HH:mm:ss"),
            @Mapping(target = "date", expression = "java(new java.util.Date())")
    })
    PersonVo2 convert2(Person person);

    /**
     * 从多个源对象获取属性值
     * 从方法参数列表获取属性值
     * 自定义时间格式化
     */
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "user.name"),
            @Mapping(target = "money", source = "person.money"),
            @Mapping(target = "describe", source = "person.describe"),
            @Mapping(target = "createTime", expression = "java(com.yzm.mapstruct.mapper.format.LocalDateTimeFormat.toStr(person.getCreateTime()))")
    })
    PersonVo3 convert3(Person person, User user, Integer id);

    // 集合映射
    List<PersonVo> converts(List<Person> persons);

    @MapMapping(valueDateFormat = "dd.MM.yyyy")
    Map<String, String> dateMap(Map<Long, Date> source);
}
