package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 41, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 左边、右边、基准数(默认数组第一个数，该数保持在tmp变量，所以空出了该位置可被覆盖)
            int left = low, right = high, tmp = arr[left];

            while (left < right) {
                // 从右向左找第一个小于tmp的数
                while (left < right && arr[right] >= tmp) {
                    // 最右边比基准数大，往左移一位
                    right--;
                }
                if (left < right) {
                    // 跳出了上面的循环说明，右边的小于基准数了，开始交换：右边的赋值给左边的
                    arr[left] = arr[right];
                    left++;
                }

                // 从左向右找第一个大于等于x的数
                while (left < right && arr[left] < tmp) {
                    left++;
                }
                if (left < right) {
                    arr[right] = arr[left];
                    right--;
                }
            }

            // 经上面循环后，左边的都小于基准数、右边的都大于基准数
            arr[left] = tmp;

            // 递归调用
            quickSort(arr, low, left - 1);
            quickSort(arr, left + 1, high);
        }
    }
}
