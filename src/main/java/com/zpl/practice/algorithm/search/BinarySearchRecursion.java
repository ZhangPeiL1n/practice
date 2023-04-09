package com.zpl.practice.algorithm.search;

/**
 * 二分查找法 - 递归
 * 二分法的前提是元素有序
 *
 * @author ZhangPeiL1n
 * @date 2022/8/14 14:40
 **/
public class BinarySearchRecursion {

    public static int binarySearch(int[] source, int target, int low, int high) {
        // 这里不用在意求和是奇数的情况，java 会向下取整，即 5 / 2 = 2
        int mid = (low + high) / 2;
        if (source[mid] == target) {
            return mid;
        }
        if (source[mid] < target) {
            low = mid + 1;
            return binarySearch(source, target, low, high);
        }
        if (source[mid] > target) {
            high = mid - 1;
            return binarySearch(source, target, low, high);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(source, 6, 0, source.length));
    }

}
