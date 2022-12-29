package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 第一轮：记录下标默认是(0=第一个元素=第一轮数)，假设值为最小值；逐个跟下一个元素进行比较，
 *        如果最小值比之后的元素大则交换下标，最终比较记录的下标是否等于轮数，不等于则交换元素，结束本轮。
 * 第二轮：经第一轮已在0号下标固定了最小值，第二轮从1号下标开始重复上面的步骤
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 4, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int count = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            // 用来记录最小值的索引位置，默认值为i
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (arr[minIndex] > arr[j]) {
                    // 遍历 i+1 ~ length 的值，找到其中最小值的位置
                    minIndex = j;
                }
            }
            // 交换当前索引 i 和最小值索引 minIndex 两处的值
            if (i != minIndex) {
                arr[i] = arr[minIndex] ^ arr[i];
                arr[minIndex] = arr[minIndex] ^ arr[i];
                arr[i] = arr[minIndex] ^ arr[i];
            }
            // 执行完一次循环，当前索引 i 处的值为最小值，直到循环结束即可完成排序
        }

        System.out.println("count = " + count);
    }
}
