package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 以升序为例
 * 1. 将数组分为两个子集，排序的和未排序的，每一轮从未排序的子集中选出最小的元素，放入排序子集
 * 2. 重复以上步骤，直到整个数组有序
 * 优化方式
 * 1. 为减少交换次数，每一轮可以先找最小的索引，在每轮最后再交换元素
 *
 * 与冒泡排序比较
 * 1. 二者平均时间复杂度都是 O(n²)
 * 2. 选择排序一般要快于冒泡，因为其交换次数少
 * 3. 但如果集合有序度高，冒泡优于选择
 * 4. 冒泡属于稳定排序算法，而选择属于不稳定排序
 */
public class SelectionSortDemo {
    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 1, 6, 2, 7, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            // 用来记录最小值的索引位置，默认值为i
            int min = i;
            int count = 0;
            for (int j = min + 1; j < arr.length; j++) {
                count++;
                if (arr[min] > arr[j]) {
                    // 遍历 i+1 ~ length 的值，找到其中最小值的位置
                    min = j;
                }
            }
            // 交换当前索引 i 和最小值索引 min 两处的值
            // 执行完一次循环，当前索引 i 处的值为最小值，直到循环结束即可完成排序
            if (i != min) {
                swap(arr, i, min);
            }
            System.out.println("比较次数：" + count);
            System.out.println("第" + i + "轮排序：" + Arrays.toString(arr));
            sum += count;
        }

        System.out.println("总比较次数：" + sum);
    }


    public static void swap(int[] ints, int i, int j) {
        ints[i] = ints[i] ^ ints[j];
        ints[j] = ints[i] ^ ints[j];
        ints[i] = ints[i] ^ ints[j];
    }
}
