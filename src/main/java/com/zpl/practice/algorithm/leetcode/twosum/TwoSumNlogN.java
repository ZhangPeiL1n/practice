package com.zpl.practice.algorithm.leetcode.twosum;

import java.util.Arrays;

/**
 * 两数之和
 * 二分查找法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/9 18:37
 **/
public class TwoSumNlogN {
    public static void main(String[] args) {
        int[] inputArr = new int[]{3, 9, 2, 7, 11, 15};
        int target = 26;

        System.out.println(Arrays.toString(twoSum(inputArr, target)));
    }

    public static int[] twoSum(int[] source, int target) {
        int[] result = new int[2];
        // 使用二分法的前提是数组有序，但不能在原数组基础上进行排序
        int[] clone = source.clone();
        Arrays.sort(clone);

        for (int i : clone) {
            // 二分法查找值
            int resultNum = binarySearch(clone, target - i);
            // 找到值在原数组中找下标
            if (-1 != resultNum) {
                result[0] = findIndex(source, i, -1);
                result[1] = findIndex(source, resultNum, result[0]);
                return result;
            }
        }
        return result;
    }

    public static int binarySearch(int[] source, int target) {
        int result = -1;

        int leftIndex = 0;
        int rightIndex = source.length - 1;
        while (leftIndex <= rightIndex) {
            // 二分法的非递归实现注意要加左确界 leftIndex，不加左确界值计算的实际上是左确界的偏移量
            // 右确界 - 左确界 = offset，但是起始位置是左确界，否则在计算大于 target 的值时左确界值不对
            int mid = (rightIndex - leftIndex) / 2 + leftIndex;
            if (target == source[mid]) {
                return source[mid];
            } else if (target < source[mid]) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }
        return result;
    }

    public static int findIndex(int[] source, int target, int forbiddenIndex) {
        for (int i = 0; i < source.length; i++) {
            // 因为元素不能重复，所以下标不能相同，forbiddenIndex 就是第一个数的下标
            // 当数组中存在两个相同的数字时，获取不同的下标
            if (target == source[i] && i != forbiddenIndex) {
                return i;
            }
        }
        return -1;
    }
}
