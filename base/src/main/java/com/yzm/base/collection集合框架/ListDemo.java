package com.yzm.base.collection集合框架;

import java.util.*;

public class ListDemo {

    private static List<String> list = new ArrayList<>();

    static {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
    }

    public static void main(String[] args) {
//        demo01();
//        demo01_2();
//        demo02();
//        demo03();
//        demo04();
//        demo04_1();
//        demo04_2();
//        demo04_3();
        demo05();
        LinkedList<Object> objects = new LinkedList<>();
        objects.add("");
    }

    //元素增删改查操作
    private static void demo01() {
        System.out.println("list = " + list);
        List<String> list2 = new ArrayList<>(list);
        System.out.println("list2 = " + list2);
        //默认在尾部添加元素
        list2.add("e");
        //指定索引位置添加元素
        list2.add(0, "f");
        System.out.println("list2 = " + list2);
        //移除指定索引的元素
        System.out.println("移除的元素：" + list2.remove(0));
        System.out.println("list2 = " + list2);
        //修改指定索引的元素
        System.out.println("修改的元素：" + list2.set(2, "C"));
        System.out.println("list2 = " + list2);
        //获取指定索引的元素
        System.out.println(list2.get(2));
        //移除指定的元素
        System.out.println(list2.remove("C"));
        System.out.println("list2 = " + list2);
        //清空所有元素
        list2.clear();
        System.out.println("list2 = " + list2);
    }

    private static void demo01_2() {
        List<String> list2 = new ArrayList<>();
        //添加整个集合的元素
        list2.addAll(list);
        System.out.println("list2 = " + list2);
        //移除整个集合的元素
        list2.add("A");
        list2.removeAll(list);
        System.out.println("list2 = " + list2);
        //保留整个集合的元素，不在集合内的移除
        list2.addAll(0, list);
        System.out.println("list2 = " + list2);
        list2.retainAll(list);
        System.out.println("list2 = " + list2);
    }

    private static void demo02() {
        //容器元素个数
        System.out.println(list.size());
        //容器是否为空
        System.out.println(list.isEmpty());
        //容器中是否有‘a’元素，没有返回-1，有返回对应的索引位置
        System.out.println(list.indexOf("a"));
        //容器中是否有‘a’元素，没有返回-1，有返回对应的索引位置，从尾部开始查找
        System.out.println(list.lastIndexOf("a"));
        //容器中是否包含‘a’元素，没有返回false，有返回true
        System.out.println(list.contains("a"));
    }


    private static void demo03() {
        String[] strings = list.toArray(new String[0]);
        System.out.println("strings = " + Arrays.toString(strings));
        String[] strings2 = list.toArray(new String[6]);
        System.out.println("strings2 = " + Arrays.toString(strings2));
        String[] strings3 = list.toArray(new String[]{});
        System.out.println("strings3 = " + Arrays.toString(strings3));
    }

    //不报错，但元素显示与预想的不一致
    private static void demo04() {
        System.out.println("list = " + list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + "=" + list.get(i));
            if ("a".equals(list.get(i))) list.remove(i);
        }
        System.out.println("list = " + list);
    }

    //直接报错，并发修改异常
    private static void demo04_1() {
        System.out.println("list = " + list);
        for (String s : list) {
            System.out.println(s);
            if ("a".equals(s)) list.remove(s);
        }
        System.out.println("list = " + list);
    }

    //直接报错，并发修改异常
    private static void demo04_2() {
        System.out.println("list = " + list);
        list.forEach(s -> {
            System.out.println(s);
            if ("a".equals(s)) list.remove(s);
        });
        System.out.println("list = " + list);
    }

    //正常
    private static void demo04_3() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            System.out.println("next = " + next);
            if ("a".equals(next)) it.remove();
        }
        System.out.println(list);

    }

    private static void demo05() {
        ListIterator<String> lli = list.listIterator();
        System.out.println("初始下标：" + lli.nextIndex());
        while (lli.hasNext()) {
            String next = lli.next();
            System.out.println("正向遍历 ==> {下标=" + lli.nextIndex() + ", 元素=" + next + "}");
            if ("c".equals(next)) break;
        }

        System.out.println("list = " + list);
        lli.set("C");
        System.out.println("list = " + list);

        System.out.println(lli.nextIndex());
        lli.add("e");
        System.out.println("list = " + list);

        while (lli.hasPrevious()) {
            String previous = lli.previous();
            System.out.println(previous);
        }

    }

}
