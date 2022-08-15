package com.zpl.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 选择排序是每趟选出最大的或最小的，只交换一次
 * O(n2)
 *
 * @author ZhangPeiL1n
 * @date 2022/8/14 14:27
 **/
public class SelectionSort {

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                // 找出每趟最大的
                if (arr[j] > arr[maxIndex]){
                    maxIndex = j;
                }
            }
            swap(arr,i,maxIndex);
        }
        return arr;
    }

    public static void swap(int[] arr, int index, int swapIndex) {
        int tmp = arr[index];
        arr[index] = arr[swapIndex];
        arr[swapIndex] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 1, 4};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }
}
