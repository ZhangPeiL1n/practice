package com.zpl.practice.algorithm.leetcode.mergesortedarray;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 顺序归并
 *
 * @author ZhangPeiL1n
 * @date 2023/4/11 17:36
 **/
public class MergeSort {
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
        // 这里我选择先复制一遍 nums1，因为最后结果要在 nums1 中
        // 所以使用复制出来的 nums1Copy 和 nums2 向 nums1 中灌就行了
        int[] nums1Copy = Arrays.copyOf(nums1, m);
        int i = 0;
        int j = 0;
        int sortedIndex = 0;
        for (; i < m && j < n; sortedIndex++) {
            if (nums1Copy[i] <= nums2[j]) {
                nums1[sortedIndex] = nums1Copy[i];
                i++;
            } else {
                nums1[sortedIndex] = nums2[j];
                j++;
            }
        }
        if (i == m && j < n) {
            System.arraycopy(nums2, j, nums1, sortedIndex, n - j);
        } else if (j == n && i < m) {
            System.arraycopy(nums1Copy, i, nums1, sortedIndex, m - i);
        }
    }
}
