package com.yzm.base.collection集合框架.Map;

import java.util.HashMap;
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
    }
}
