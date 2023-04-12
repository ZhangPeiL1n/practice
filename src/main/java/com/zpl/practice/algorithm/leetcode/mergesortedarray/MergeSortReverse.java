package com.zpl.practice.algorithm.leetcode.mergesortedarray;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 逆序归并
 *
 * @author ZhangPeiL1n
 * @date 2023/4/11 17:36
 **/
public class MergeSortReverse {
    public static void main(String[] args) {
//        int[] nums1 = new int[]{1, 1, 6, 10, 11, 12, 0, 0, 0, 0, 0};
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        // int[] nums1 = new int[]{1, 1, 6, 10, 11, 12, 2, 8, 11, 11, 13}};


//        int[] nums2 = new int[]{2, 8, 11, 11, 13};
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int sortedIndex = m + n - 1;
        for (; i >= 0 && j >= 0; sortedIndex--) {
            if (nums1[i] >= nums2[j]) {
                nums1[sortedIndex] = nums1[i];
                i--;
            } else {
                nums1[sortedIndex] = nums2[j];
                j--;
            }
        }
        // nums1 排完的话，说明 num2剩下的[0,j] 的元素都比 nums1[0] 要小
        // 那么直接将 nums2 中倒序剩余的复制到 nums1 从0开始的位置即可
        if (i < 0) {
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }
    }
}
