package com.yzm.base.collection集合框架;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);

        list.remove();
        System.out.println(list);
    }
}
