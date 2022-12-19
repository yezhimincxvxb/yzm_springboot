package base.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class HillSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 41, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        hillSort(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }

    public static void hillSort(int[] arr) {
        //length = 10，则增量序列gap = 5、 2、 1
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 插入排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap; j -= gap) {
                    //增量的左右元素进行比较并交换
                    if (arr[j - gap] > arr[j]) {
                        arr[j - gap] = arr[j - gap] ^ arr[j];
                        arr[j] = arr[j - gap] ^ arr[j];
                        arr[j - gap] = arr[j - gap] ^ arr[j];
                    } else {
                        break;
                    }
                }
            }
        }
    }

}
