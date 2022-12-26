package com.yzm.fastjson;

import com.alibaba.fastjson.JSON;
import com.yzm.fastjson.entuty.Student02;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JSONTest2 {

    @Test
    public void demo01() {
        Student02 build = Student02.builder().id(11).name("zhangsan").age(10).build();
        System.out.println(JSON.toJSONString(build));
    }

}
