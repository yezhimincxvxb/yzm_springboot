package com.yzm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.com.yzm.mapper")
public class MybatisConfig {
}
