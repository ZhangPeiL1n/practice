package com.zpl.practice.algorithm.leetcode.mergesortedarray;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 插入排序
 *
 * @author ZhangPeiL1n
 * @date 2023/4/11 17:36
 **/
public class MergeSortedArray {
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
        if (n >= 0) {
            System.arraycopy(nums2, 0, nums1, m, n);
        }
        int i = 0;
        int j = m;
        // 这里做了修改，课里将指针和计数分开写，多了 countI 和 countJ 两个计数
        // 课里使用计数为依据，而不是以指针为依据
        // 以计数为依据，那么只要 countI == m 或 countJ == n 就说明排序完成了
        // 但是以指针为依据的话，因为插入排序每次插入后后续元素都会向后移动一位，所以指针 i 就不能小于 m，得小于 m + n，也就是最差的情况 nums2 都要插到 nums1 当中
        // 但是这个方法也有问题，可能会出现 i > j 的情况，当 i == j 时候其实排序就已经完成了
        // 所以这里加了 i != j 的判断
        // 并且以 i = 0 和 i < m + n，j = m 和 j < m + n 为依据
        // 指针 i 从 nums1 开头开始扫，指针 j 从 nums1 后半部分 nums2 的数据开始扫
        for (; i < m + n && j < m + n && i != j; i++) {
            if (nums1[i] > nums1[j]) {
                int tmpValue = nums1[j];
                // 从 i 到 j 的元素全部向后一移一位
                for (int x = j; x != i; x--) {
                    nums1[x] = nums1[x - 1];
                }
                // 把 nums1[j] 放到 nums1[i] 上
                nums1[i] = tmpValue;
                j++;
            }
        }
    }
}
