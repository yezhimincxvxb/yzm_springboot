package com.yzm.base.sort;

import java.util.Scanner;

/**
 * 二分查找法：
 * 1. 前提:有已排序数组 A(假设已经做好)
 * 2. 定义左边界 L、右边界 R，确定搜索范围，循环执行二分查找(3、4两步)
 * 3. 获取中间索引M=Floor((L+R)/2)
 * 4. 中间索引的值 A[M] 与待搜索的值T进行比较
 *     A[M]==T表示找到，返回中间索引
 *     A[M]>T，中间值右侧的其它元素都大于T，无需比较，中间索引左边去找，M-1设置为右边界，重新查找
 *     A[M]<T，中间值左侧的其它元素都小于T，无需比较，中间索引右边去找，M+1设置为左边界，重新查找
 * 5. 当L>R时，表示没有找到，应结束循环
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        int[] ints = {8, 23, 30, 32, 54, 66, 70, 87};
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String param = scanner.next();
            if ("q".equals(param)) {
                break;
            }
            System.out.println(binarySearch(ints, Integer.parseInt(param)));
        }
    }

    private static int binarySearch(int[] ints, int num) {
        if (ints == null || ints.length == 0) return -1;

        // l：左边界，r：右边界，mid：中间
        int l = 0, r = ints.length - 1, mid;

        while (l <= r) {
            // 需考虑 (l + r) 的值大于int最大值的情况，使用无符号右移可以解决该问题
            mid = (l + r) >>> 1;
            if (ints[mid] == num) {
                return mid;
            } else if (ints[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1;
    }

}
