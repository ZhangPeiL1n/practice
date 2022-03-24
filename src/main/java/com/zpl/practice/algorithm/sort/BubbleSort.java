package com.zpl.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author ZhangPeilin
 * @date 2018/11/14
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {

        //外层控制次数，n个数，最多比较n次
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = false;
            // 内层比较大小
            // 每走一趟，后面就会多一位有序的数，所以就可以少交换一次
            // 比如第一趟走完，最后一个数一定是最大的，按最大的往后这种情况
            // 那么第二趟就不用比较倒数第二位和最后一位了，因为最后一位一定是最大的
            // 所以这里循环是 arr.length - 1 后再 - i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //每次比较相邻的两个，将大的值往后放
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    // flag 表示发生交换
                    flag = true;
                }
            }
            // flag 为 false 时表示这一趟内层排序没有发生交换，
            // 那就说明已经有序，不需要进行后面的遍历了
            // 因为只要不是有序的，肯定会发生交换
            if (!flag) {
                break;
            }
        }
    }

    public static void swap(int[] arr, int index, int swapIndex) {
        int tmp = arr[index];
        arr[index] = arr[swapIndex];
        arr[swapIndex] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 1, 4};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
