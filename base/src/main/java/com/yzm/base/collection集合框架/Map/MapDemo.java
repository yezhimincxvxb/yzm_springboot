package com.yzm.base.collection集合框架.Map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("1", new Object());
        map.put(null, new Object());
        map.put("2", "aaa");
        map.put("4", null);
        map.put("3", 101);
        System.out.println("map = " + map);
        System.out.println("get = " + map.get("2"));

        // 查看keySet源码
        Set<Object> keys = map.keySet();
        String toString = keys.toString();
        System.out.println("class = " + keys.getClass() + "  keys = " + toString);
        for (Object object : keys) {
            System.out.println("object = " + object);
        }

        Collection<Object> values = map.values();
        String toString1 = values.toString();
        System.out.println("class = " + values.getClass() + "  values = " + toString1);
        for (Object value : values) {
            System.out.println("value = " + value);
        }

        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println("key = " + entry.getKey());
            System.out.println("value = " + entry.getValue());
        }

    }
}
