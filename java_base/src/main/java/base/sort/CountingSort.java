package base.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, -3, 41, 13, 16, 6, 3, 9, 8};
        System.out.println("原数组： " + Arrays.toString(arr));
        int[] ints = countSort(arr);
        System.out.println("排序后： " + Arrays.toString(ints));
    }

    public static int[] countSort(int[] arr) {
        // 求最大、最小值
        int min = arr[0], max = arr[0];
        for (int a : arr) {
            if (a < min) {
                min = a;
            }
            if (a > max) {
                max = a;
            }
        }

        // 计数数组，元素大小的极值差+1
        int[] count = new int[max - min + 1];

        // 统计数组中每个元素出现的次数，存入数组C
        for (int a : arr) {
            count[a - min]++;
        }

        // 计数累加：从第一个元素开始，每一项和前一项相加）
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // 填充目标数组
        int[] target = new int[arr.length];
        for (int a : arr) {
            target[count[a - min] - 1] = a;
            count[a - min]--;
        }
        return target;
    }
}
