package com.yzm.datamasking.config;

public interface DataMaskingOperation {

    String mask(String content, String maskChar);
}
