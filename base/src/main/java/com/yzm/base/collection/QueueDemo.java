package com.yzm.base.collection;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueDemo {
    public static void main(String[] args) {
//        test01();
        test02();
    }

    /**
     * 使用与类PriorityQueue相同的排序规则并提供阻塞检索操作的无界阻塞队列。
     * 相比较PriorityQueue多了PriorityQueue锁机制
     */
    private static void test02() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queue.add(3);
        queue.add(9);
        queue.add(3);
        queue.add(6);
        queue.add(1);
        queue.add(1);
        System.out.println("queue = " + queue);
    }

    /**
     * 基于优先级堆的无界优先级队列。（无界：会自动扩容，初始11，队列大小小于64时，扩容为原来的2倍，大于64是1.5倍）
     * 数组实现、非线程安全、默认自然排序(可根据比较器排序)、不允许null元素
     * 元素可重复，有重复元素时，仅保证队列的头部是相对于指定排序的最小元素。如果多个元素以最低值绑定，则头部是这些元素之一
     * 常用方法：
     * offer/add：头部添加元素
     * poll：获取头部第一个元素并将该元素从队列移除
     * remove：跟poll相似，不同的是如果队列size=0则抛异常
     * peek：获取头部第一个元素但不会将该元素从队列移除
     * element：跟peek相似，不同的是如果队列size=0则抛异常
     */
    private static void test01() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(6);
        queue.offer(2);
        queue.offer(1);
        queue.offer(1);
        queue.offer(5);
        queue.offer(4);
        System.out.println("queue = " + queue);

        int leg = queue.size();
        for (int i = 0; i < leg; i++) {
            System.out.println(queue.poll());
        }

        System.out.println("queue = " + queue);
    }
}
