package base.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 4, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        sort2(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int count = 0;

        // 外循环扫描无序序列
        for (int i = 1; i < arr.length; i++) {
            int head = i; // 无序序列头部元素
            int tail = i - 1; // 有序序列尾部元素
            while (tail >= 0 && arr[head] < arr[tail]) {
                count++;
                arr[head] = arr[head] ^ arr[tail];
                arr[tail] = arr[head] ^ arr[tail];
                arr[head] = arr[head] ^ arr[tail];
                // 右边比左边的小，交换位置后，继续往前扫描相邻的元素
                head--;
                tail--;
            }
        }

        System.out.println("count = " + count);
    }

    public static void sort2(int[] arr) {
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
