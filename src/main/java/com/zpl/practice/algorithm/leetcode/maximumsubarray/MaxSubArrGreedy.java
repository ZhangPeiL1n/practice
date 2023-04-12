package com.zpl.practice.algorithm.leetcode.maximumsubarray;

/**
 * 最大子数组和
 * 贪心算法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/10 15:43
 **/
public class MaxSubArrGreedy {

    public static void main(String[] args) {
        // int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(input));
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            // 求当前和
            sum += num;
            // 比较最大值
            if (result < sum) {
                result = sum;
            }
            // 和小于0就归零，这一段的和就不要了
            if (sum < 0) {
                sum = 0;
            }
        }
        return result;
    }
}
