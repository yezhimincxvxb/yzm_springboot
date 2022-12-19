package base.sort;

import java.util.*;

/**
 * 桶排序 时间复杂度：O(n)
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 13, 16, 6, 3, 9, 8};
        System.out.println("arr = " + Arrays.toString(arr));
        bucketSort2(arr); // 桶排序
        System.out.println("arr = " + Arrays.toString(arr));
    }

    public static void bucketSort(int[] arr) {
        // 遍历原始数组，找到数组中的最大、最小值
        int min = arr[0], max = arr[0];
        for (int a : arr) {
            min = a < min ? a : min;
            max = a < max ? a : max;
        }

        // 创建桶数组
        int[] buckets = new int[max - min + 1];

        // 再次遍历原始数组，得到原数组中存在的各个元素，以及出现的次数
        for (int a : arr) {
            buckets[a - min]++;
        }

        // 遍历桶数组,外层循环从桶的第一位开始（即下表为零）；内层循环遍历桶数组中下标为i的值出现的次数
        for (int index = 0, i = 0; i < buckets.length; i++) {
            for (int j = buckets[i]; j > 0; j--) {
                arr[index++] = i + min;
            }
        }
    }

    public static void bucketSort2(int[] arr) {
        // 最大、最小值
        int min = arr[0], max = arr[0];
        for (int a : arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }

        // 桶的个数、区间偏移量
        int bucketNum =  5;
        int offset = (max - min + 1) / bucketNum;
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new LinkedList<>());
        }

        // 将每个元素放入桶
        for (int a : arr) {
            int num = (a - min) / offset;
            sort(buckets.get(num), a);
        }

        // 将桶中元素全部取出来并放入 arr 中输出
        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            if (bucket.isEmpty()) continue;

            for (Integer data : bucket) {
                arr[index++] = data;
            }
        }
    }

    private static void sort(List<Integer> list, int data) {
        ListIterator<Integer> listIterator = list.listIterator();
        boolean flag = true;
        while (listIterator.hasNext()) {
            if (data < listIterator.next()) {
                listIterator.previous();
                listIterator.add(data);
                flag = false;
                break;
            }
        }
        if (flag) list.add(data);
    }

}
