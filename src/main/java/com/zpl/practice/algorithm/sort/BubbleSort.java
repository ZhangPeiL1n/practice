package com.zpl.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 冒泡排序每趟都选出最大的或者最小的，每次比较都交换
 * @author ZhangPeilin
 * @date 2018/11/14
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                //每次比较相邻的两个，将大的值往后放
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
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
