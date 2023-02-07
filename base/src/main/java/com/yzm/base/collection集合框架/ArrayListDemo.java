package com.yzm.base.collection集合框架;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("all")
public class ArrayListDemo {
    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }

    private static void demo03() {
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println("next = " + next);
        }

        // 增强for循环，底层实现仍是迭代器
        for (Object o :list) {
            System.out.println("o = " + o);
        }
    }

    private static void demo02() {
        // 添加10个元素
        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("A" + i);
        }

        // list2有6个元素，然后一次性添加到list中
        ArrayList list2 = new ArrayList();
        for (int i = 0; i < 6; i++) {
            list2.add("B" + i);
        }

        list.addAll(list2);
        list.add("C0");
    }

    private static void demo01() {
        ArrayList list = new ArrayList();

        // 添加元素
        // 首次扩容
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        // 第二次扩容
        list.add(100);
        System.out.println("list = " + list);

        list.iterator();

        // 移除元素
        System.out.println(list.remove(2));
        System.out.println(list.remove(2));
    }
}
