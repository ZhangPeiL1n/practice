package com.zpl.practice.algorithm.leetcode.maximumsubarray;

/**
 * 最大子数组和
 * 动态规划
 *
 * @author ZhangPeiL1n
 * @date 2023/4/11 10:47
 **/
public class MaxSubArrDP {

    public static void main(String[] args) {
        // int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(input));
    }

    public static int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        if (nums.length == 0) {
            return 0;
        }
        int[] dpArr = new int[nums.length];

        dpArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 无后效性：dp[i] 只与 dp[i-1] 的值有关
            dpArr[i] = Math.max(nums[i] + dpArr[i - 1], nums[i]);
        }
        for (int dp : dpArr) {
            if (result < dp) {
                result = dp;
            }
        }
        return result;
    }
}
