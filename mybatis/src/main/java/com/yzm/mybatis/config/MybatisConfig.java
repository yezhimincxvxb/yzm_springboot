package com.yzm.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.mybatis.mapper")
public class MybatisConfig {
}
