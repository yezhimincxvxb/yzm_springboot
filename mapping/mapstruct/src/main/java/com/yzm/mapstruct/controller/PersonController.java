package com.yzm.mapstruct.controller;

import com.yzm.mapstruct.entity.Person;
import com.yzm.mapstruct.entity.SexEnum;
import com.yzm.mapstruct.entity.Teacher;
import com.yzm.mapstruct.entity.User;
import com.yzm.mapstruct.mapper.PersonMapper;
import com.yzm.mapstruct.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    static Person person;

    static {
        person = new Person();
        person.setId(10001L);
        person.setName("张三");
        person.setAge(43);
        person.setWeight(120.0F);
        person.setHeight(172.5F);
        person.setMoney(new BigDecimal("3500.1234"));
        person.setDescribe("上班族");
        person.setCreateTime(LocalDateTime.now());
        person.setUpdateTime(new Date());
    }

    @Autowired
    private PersonMapper personMapper;

    @GetMapping("demo01")
    public void demo01() {
        System.out.println(person);
        System.out.println(personMapper.convert(person));
    }


    @GetMapping("demo02")
    public void demo02() {
        System.out.println(person);
        person.setDescribe(null);
        System.out.println(personMapper.convert2(person));
    }

    @GetMapping("demo03")
    public void demo03() {
        System.out.println(person);
        User user = new User();
        user.setId(100);
        user.setName("李四");
        user.setCreateTime(LocalDateTime.now());
        System.out.println(personMapper.convert3(person, user, 999));
    }

    @GetMapping("demo04")
    public void demo04() {
        List<Person> list = new ArrayList<>();
        list.add(person);
        System.out.println(personMapper.converts(list));
    }

    @GetMapping("demo05")
    public void demo05() {
        Map<Long, Date> map = new HashMap<>();
        map.put(10L, new Date());

        System.out.println(personMapper.dateMap(map));
    }

    @GetMapping("demo06")
    public void demo06() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("叶老师");
        teacher.setGender(SexEnum.MAN);
        teacher.setDate(new Date());
        System.out.println(TeacherMapper.INSTANCE.convert(teacher));
    }

}
