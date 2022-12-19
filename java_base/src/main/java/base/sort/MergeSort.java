package base.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 2, 3, 41, 13, 16, 6, 3, 9, 8};
        System.out.println("排序前： " + Arrays.toString(arr));
        int[] tmp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, tmp);
        System.out.println("排序后： " + Arrays.toString(arr));
    }


    public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            // 拆分
            int mid = (low + high) / 2; // 平分成左右序列
            mergeSort(arr, low, mid, tmp); //对左边序列继续分解
            mergeSort(arr, mid + 1, high, tmp);  //对右边序列继续分解

            // 合并
            //将arr数组元素存储在辅助数组tmp中
            for (int i = low; i <= high; i++) {
                tmp[i] = arr[i];
            }

            //left：左边有序序列的初始索引：low、最大索引应是：mid
            //right：右边有序序列的初始索引：mid + 1、最大索引应是：high
            //index：当前要插入的位置，初始位置：low，最大索引位置：high
            int left = low, right = mid + 1, index = low;
            while (index <= high) {
                // 左序列或右序列已超出边界
                if (left > mid || right > high) {
                    if (left > mid) {
                        arr[index] = tmp[right++];
                    } else {
                        arr[index] = tmp[left++];
                    }
                } else {
                    if (tmp[left] < tmp[right]) {
                        arr[index] = tmp[left++];
                    } else {
                        arr[index] = tmp[right++];
                    }
                }
                index++;
            }
        }
    }

    /**
     * 合并
     */
    public static void merge1(int[] arr, int low, int mid, int high, int[] tmp) {
        //将arr数组元素存储在辅助数组tmp中
        for (int i = low; i <= high; i++) {
            tmp[i] = arr[i];
        }

        //left：左边有序序列的初始索引：low、最大索引应是：mid
        //right：右边有序序列的初始索引：mid + 1、最大索引应是：high
        //index：当前要插入的位置，初始位置：low，最大索引位置：high
        for (int left = low, right = mid + 1, index = low; index <= high; index++) {
            if (left > mid || right > high) {
                if (left > mid) {
                    arr[index] = tmp[right++];
                } else {
                    arr[index] = tmp[left++];
                }
            } else {
                if (tmp[left] < tmp[right]) {
                    arr[index] = tmp[left++];
                } else {
                    arr[index] = tmp[right++];
                }
            }

//            if (left > mid) {
//                arr[index] = tmp[right];
//                right++;
//            } else if (right > high) {
//                arr[index] = tmp[left];
//                left++;
//            } else if (tmp[left] < tmp[right]) {
//                arr[index] = tmp[left];
//                left++;
//            } else {
//                arr[index] = tmp[right];
//                right++;
//            }
        }
    }

    public static void merge1_1(int[] arr, int low, int mid, int high, int[] tmp) {
        int i = low; //初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引（右边有序序列的初始位置即中间位置的后一位置）
        int t = 0;//指向tmp数组的当前索引，初始为0

        //先把左右两边的数据（已经有序）按规则填充到tmp数组
        //直到左右两边的有序序列，有一边处理完成为止
        while (i <= mid && j <= high) {
            //如果左边有序序列的当前元素小于或等于右边的有序序列的当前元素，就将左边的元素填充到tmp数组中
            if (arr[i] <= arr[j]) {
                tmp[t] = arr[i];
                t++;//索引向后移
                i++;//i后移
            } else {
                //反之，将右边有序序列的当前元素填充到tmp数组中
                tmp[t] = arr[j];
                t++;//索引向后移
                j++;//j后移
            }
        }
        //把剩余数据的一边的元素填充到tmp中
        while (i <= mid) {
            //此时说明左边序列还有剩余元素
            //全部填充到tmp数组
            tmp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= high) {
            //此时说明左边序列还有剩余元素
            //全部填充到tmp数组
            tmp[t] = arr[j];
            t++;
            j++;
        }

        //将tmp数组的元素复制到原数组
        t = 0;
        while (low <= high) {
            arr[low] = tmp[t];
            t++;
            low++;
        }
    }

}
