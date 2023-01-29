package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 以升序为例
 *  1. 依次比较数组中相邻两个元素大小，若a[j]>alj+1]，则交换两个元素，两两都比较一遍称为一轮冒泡，结果是让最大的元素排至最后
 *  2. 重复以上步骤，直到整个数组有序
 * 优化方式:每轮冒泡时，最后一次交换索引可以作为下一轮冒泡的比较次数，如果这个值为零，表示整个数组有序，直接退出外层循环即可
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 1, 6, 2, 7, 9, 8};
        System.out.println("排序前：" + Arrays.toString(arr));
//        bubbleSort(arr);
        bubbleSort_v2(arr);
    }

    public static void bubbleSort(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {

            //n：每轮需要比较的次数。由于固定位置的元素不需要比较，所以需要-i
            int n = arr.length - 1 - i;
            for (int j = 0; j < n; j++) {
                //比较左右两边的元素
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

            System.out.println("比较次数：" + n);
            System.out.println("第" + i + "轮排序：" + Arrays.toString(arr));
            sum += n;
        }

        System.out.println("总比较次数：" + sum);
    }

    public static void bubbleSort_v2(int[] arr) {
        int sum = 0;
        //n：每轮的比较次数
        int n = arr.length - 1;

        for (int i = 0; i < arr.length - 1; i++) {
            //last：每轮最后一次的交换下标
            int last = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    last = j;
                }
            }

            System.out.println("比较次数：" + n);
            System.out.println("第" + i + "轮排序：" + Arrays.toString(arr));
            sum += n;
            n = last;
            if (n == 0) break;
        }

        System.out.println("总比较次数：" + sum);
    }

    public static void swap(int[] ints, int i, int j) {
        ints[i] = ints[i] ^ ints[j];
        ints[j] = ints[i] ^ ints[j];
        ints[i] = ints[i] ^ ints[j];
    }
}
