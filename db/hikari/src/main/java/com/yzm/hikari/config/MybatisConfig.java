package com.yzm.hikari.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.hikari.mapper")
public class MybatisConfig {
}
