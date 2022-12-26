package com.yzm.serialize.config;

import com.yzm.serialize.anno.SerializeField;
import lombok.Data;

import java.util.*;

/**
 * 存储待处理类的对象及待过滤字段信息
 */
@Data
public class JsonFilterObject {

    private Object object;

    private Map<Class<?>, Set<String>> includeMap = new HashMap<>();

    private Map<Class<?>, Set<String>> excludeMap = new HashMap<>();

    public void putToMap(SerializeField value) {
        this.putToMap(value.clazz(), value.includes(), value.excludes());
    }

    private void putToMap(Class<?> clazz, String[] includes, String[] excludes) {
        if (clazz == null) return;
        if (includes != null && includes.length > 0) {
            includeMap.put(clazz, new HashSet<>(Arrays.asList(includes)));
        }
        if (excludes != null && excludes.length > 0) {
            excludeMap.put(clazz, new HashSet<>(Arrays.asList(excludes)));
        }
    }
}
