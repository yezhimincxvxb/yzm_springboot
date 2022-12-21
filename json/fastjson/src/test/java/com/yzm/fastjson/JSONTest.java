package com.yzm.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzm.fastjson.entuty.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JSONTest {

    /**
     * Java对象-->JSON对象
     */
    @Test
    public void demo01() {
        Student build = Student.builder().id(1).name("张三").age(11).build();
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(build);
        System.out.println("jsonObject = " + jsonObject);
    }

    /**
     * Java对象-->JSON字符串
     */
    @Test
    public void demo02() {
        Student build = Student.builder().id(1).name("张三").age(11).build();
        String jsonString = JSON.toJSONString(build);
        System.out.println("jsonString = " + jsonString);
    }

    /**
     * JSON字符串-->JSON对象
     */
    @Test
    public void demo03() {
        String jsonString = "{\"age\":11,\"id\":1,\"name\":\"张三\"}";
        JSONObject jsonObject = JSON.parseObject(jsonString);
        System.out.println("jsonObject = " + jsonObject);
    }

    /**
     * JSON字符串-->Java对象
     */
    @Test
    public void demo04() {
        String jsonString = "{\"age\":11,\"id\":1,\"name\":\"张三\"}";
        Student student = JSON.parseObject(jsonString, Student.class);
        System.out.println("student = " + student);
    }

    /**
     * JSON数组字符串-->JSONArray对象
     */
    @Test
    public void demo05() {
        String jsonString = "['游泳','下棋','马拉松']";
        JSONArray jsonArray = JSON.parseArray(jsonString);
        System.out.println("jsonArray = " + jsonArray);
    }

    /**
     * JSON数组字符串-->集合对象
     */
    @Test
    public void demo06() {
        String jsonString = "['游泳','下棋','马拉松']";
        List<String> strings = JSON.parseArray(jsonString, String.class);
        System.out.println("strings = " + strings);
    }

    /**
     * JSON数组字符串-->集合对象
     */
    @Test
    public void demo07() {
        String jsonString = "[{\"age\":11,\"id\":1,\"name\":\"张三\"},{\"age\":19,\"id\":2,\"name\":\"李四\"},{\"age\":22,\"id\":3,\"name\":\"王五\"}]";
        List<Student> students = JSON.parseArray(jsonString, Student.class);
        System.out.println("students = " + students);
    }

}
