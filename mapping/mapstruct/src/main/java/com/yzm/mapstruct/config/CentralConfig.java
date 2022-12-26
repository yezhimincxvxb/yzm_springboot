package com.yzm.mapstruct.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

// 目标类有源类没有的属性字段时的策略：忽略
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CentralConfig {
}

