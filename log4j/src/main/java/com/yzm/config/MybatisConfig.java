package com.yzm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.mapper")
public class MybatisConfig {
}
