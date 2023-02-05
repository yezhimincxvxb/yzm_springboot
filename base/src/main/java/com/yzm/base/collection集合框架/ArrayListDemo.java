package com.yzm.base.collection集合框架;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();

        String s = new String("123");
        String s2 = new String("123");
        System.out.println(s == s2);
        System.out.println(s.equals(s2));
    }
}
