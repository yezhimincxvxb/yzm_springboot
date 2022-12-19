package base.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 步骤1：从左到右，相邻元素两两比较，如果左边元素比右边的大则交换位置。
 * 步骤2：第一轮循环，通过比较最终将最大的元素移动到最右边，结束本轮循环。
 * 步骤3：经第一轮循环后，已固定最大值在最后一个元素，第二轮重头开始继续比较选出第二大元素并固定在倒数第二位置上。
 * 步骤4：重复上面步骤完成排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 4, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        sort2(arr);
        System.out.println("排序后： " + Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int count = 0;

        //外层循环：控制固定位置的个数。
        //第一轮固定最后一个位置的元素，第二轮固定倒数第二位置...n个元素需固定位置n-1个位置即可
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环：控制比较的次数。每轮都从第一位开始比较，但固定位置的元素不需要比较，所以需要-i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                count++;
                //比较左右两边的元素，左 > 右 交换位置
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }

        System.out.println("count = " + count);
    }

    public static void sort2(int[] arr) {
        int count = 0;
        boolean flag;

        for (int i = 0; i < arr.length - 1; i++) {
            flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
            if (flag) break;
        }
        System.out.println("count = " + count);
    }
}
