package com.yzm.log4j.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.log4j.mapper")
public class MybatisConfig {
}
