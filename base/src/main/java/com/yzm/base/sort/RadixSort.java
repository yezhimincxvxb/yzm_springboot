package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {45, 510, 119, 2, 23, 8, 999, 17, 99};
        System.out.println("原数组： " + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));

    }

    public static void radixSort(int[] arr) {
        // 最大值max
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 最大数是几位数digits
        int digits = (max + "").length();

        // 定义一个二维数组，模拟桶，每个桶就是对应一个一维数组
        int[][] bucket = new int[10][arr.length];

        // 有几位数就需要进行几轮循环i表示循环次数，n表示倍数用来辅助获取个位数十位数等
        for (int i = 0, n = 1; i < digits; i++, n *= 10) {
            //比较每个元素的对应位数上的数字，将元素放进桶里面
            for (int j = 0, x, y = 0; j < arr.length; j++) {
                // 获取个位数、十位数、百位数...
                x = arr[j] / n % 10;
                // 将元素放入对应的桶中，初始位置0，然后位置往后移一位
                bucket[x][y++] = arr[j];
            }
            // 按照桶的顺序取出数据并放回原数组
            for (int k = 0, index = 0; k < bucket.length; k++) {
                // 对该桶进行遍历
                for (int l = 0; l < bucket[k].length; l++) {
                    // 桶中元素不等于0(初始化都是0)
                    if (bucket[k][l] != 0) {
                        // 取出元素放回原数组
                        arr[index++] = bucket[k][l];
                        // 放回原数组后，赋值为0重新使用
                        bucket[k][l] = 0;
                    }
                }
            }
        }
    }
}
