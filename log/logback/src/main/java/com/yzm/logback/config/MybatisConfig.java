package com.yzm.logback.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.logback.mapper")
public class MybatisConfig {
}
