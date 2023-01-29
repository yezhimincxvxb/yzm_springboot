package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 以升序为例
 * 1. 将数组分为两个区域，排序区域和未排序区域，每一轮从未排序区域中取出第一个元素，插入到排序区域(需保证顺序)
 * 2. 重复以上步骤，直到整个数组有序
 * <p>
 * 优化方式
 * 1. 待插入元素进行比较时，遇到比自己小的元素，就代表找到了插入位置，无需进行后续比较
 * 2. 插入时可以直接移动元素，而不是交换元素
 * <p>
 * 与选择排序比较
 * 1. 二者平均时间复杂度都是 O(n2)
 * 2. 大部分情况下，插入都略优于选择
 * 3. 有序集合插入的时间复杂度为 O(n)
 * 4. 插入属于稳定排序算法，而选择属于不稳定排序
 */
public class InsertionSortDemo {
    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 1, 6, 2, 7, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        int sum = 0;

        // 将数组分为无序和有序，第一个元素为有序，其余为无序
        for (int i = 1; i < arr.length; i++) {
            int head = arr[i]; // 无序序列的头部元素
            int tail = i - 1; // 有序序列的尾部下标
            while (tail >= 0) {
                sum++;
                if (head < arr[tail]) {
                    arr[tail + 1] = arr[tail];
                } else {
                    break;
                }
                tail--;
            }
            arr[tail+ 1] = head;

            System.out.println("第" + i + "轮排序：" + Arrays.toString(arr));
        }

        System.out.println("总比较次数：" + sum);
    }

    public static void insertSort_v2(int[] arr) {
        // 从第二个元素开始，依次与前面的元素比较
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    arr[j] = arr[j] ^ arr[j - 1];
                    arr[j - 1] = arr[j] ^ arr[j - 1];
                    arr[j] = arr[j] ^ arr[j - 1];
                } else {
                    break;
                }
            }
        }
    }
}
