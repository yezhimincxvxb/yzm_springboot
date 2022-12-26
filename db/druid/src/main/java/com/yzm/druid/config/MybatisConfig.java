package com.yzm.druid.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yzm.druid.mapper")
public class MybatisConfig {
}
