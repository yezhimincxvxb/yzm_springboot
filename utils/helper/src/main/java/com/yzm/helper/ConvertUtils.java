package com.yzm.helper;

import java.lang.reflect.Field;
import java.util.Objects;

public class ConvertUtils {

    /**
     * 同类型，非null字段赋值
     *
     * @param source 源类
     * @param target 目标类
     */
    public <T> void mergeObject(T source, T target) {
        if (source == null || target == null) return;
        if (!Objects.equals(source.getClass(), target.getClass())) return;

        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Objects.isNull(field)) continue;
            // 忽略UUID
            if (field.toGenericString().contains("static final long")) {
                continue;
            }

            try {
                field.setAccessible(true);
                Object value = field.get(source);
                if (null != value && !Objects.equals("", value)) field.set(target, value);
                field.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
