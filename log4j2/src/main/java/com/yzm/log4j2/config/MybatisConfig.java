package com.yzm.log4j2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.log4j2.mapper")
public class MybatisConfig {
}
