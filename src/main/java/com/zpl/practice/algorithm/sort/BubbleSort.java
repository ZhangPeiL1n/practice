package com.zpl.practice.algorithm.sort;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 冒泡排序
 *
 * @author ZhangPeilin
 * @date 2018/11/14
 */

public class BubbleSort {

    //public static  void bubbleSort(int[] arr){
    //    //外层控制次数，n个数，最多比较n次
    //    for (int i = 0; i < arr.length - 1; i ++){
    //        //内层比较大小
    //        for (int j = 0; j < arr.length - 1; j++){
    //            //交换
    //            if (arr[j] > arr[j + 1]){
    //                int tmp = arr[j];
    //                arr[j] = arr[j + 1];
    //                arr[j + 1] = tmp;
    //            }
    //        }
    //    }
    //}
    //public static  void bubbleSort(int[] arr){
    //
    //    //外层控制次数，n个数，最多比较n次
    //    for (int i = 0; i < arr.length - 1; i ++){
    //        //内层比较大小
    //        for (int j = 0; j < arr.length - i - 1; j++){
    //            //交换
    //            if (arr[j] > arr[j + 1]){
    //                int tmp = arr[j];
    //                arr[j] = arr[j + 1];
    //                arr[j + 1] = tmp;
    //            }
    //        }
    //    }
    //}

    public static void bubbleSort(int[] arr) {
        boolean flag = false;

        //外层控制次数，n个数，最多比较n次
        for (int i = 0; i < arr.length - 1; i++) {
            //内层比较大小
            for (int j = 0; j < arr.length - i - 1; j++) {
                //交换
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        HashMap<Object, Object> hashMap = new HashMap<>();
        // hashMap.contains
    }
}
