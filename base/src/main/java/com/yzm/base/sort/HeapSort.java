package com.yzm.base.sort;

import java.util.Arrays;

/**
 * 堆排序 时间复杂度：O(nlogn)
 * 1.首先将待排序的数组构造成一个大根堆，此时，整个数组的最大值就是堆结构的顶端
 * 2.将顶端的数与末尾的数交换，此时，末尾的数为最大值，剩余待排序数组个数为n-1
 * 3.将剩余的n-1个数再构造成大根堆，再将顶端数与n-1位置的数交换，如此反复执行，便能得到有序数组
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 41, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前" + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("排序后" + Arrays.toString(arr));

    }

    public static void heapSort(int[] arr) {
        /**
         * 堆排序-升序
         * 生成大根堆
         * 最后一个非叶子节点：arr[arr.length / 2 - 1]，由该节点往根节点调整
         */
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /**
         * 将堆项元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 重新调整结构，使其满足堆定义，然后继续交换堆项元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            arr[j] = arr[j] ^ arr[0];
            arr[0] = arr[j] ^ arr[0];
            arr[j] = arr[j] ^ arr[0];

            adjustHeap(arr, 0, j);
        }
    }


    /**
     * 将一个数组（树）调整成一个大根堆
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 左右子节点
        int left = i * 2 + 1, right = i * 2 + 2;
        int max;
        while (left < length) {
            // 左右节点的最大值
            if (right < length && arr[left] < arr[right]) {
                max = right;
            } else {
                max = left;
            }

            // 父节点比子节点都大，则不需要往下调整了
            if (arr[i] > arr[max]) {
                break;
            }

            // 交换元素
            arr[i] = arr[i] ^ arr[max];
            arr[max] = arr[i] ^ arr[max];
            arr[i] = arr[i] ^ arr[max];

            // 当前节点底下还有非叶子节点时
            i = max;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }
}
