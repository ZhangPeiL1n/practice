package com.zpl.practice.algorithm.leetcode.maximumsubarray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 最大子数组和
 *
 * @author ZhangPeiL1n
 * @date 2023/4/10 15:43
 **/
public class MaxSubArr {

    public static void main(String[] args) {
        // int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(input));
    }

    // public static int maxSubArray(int[] nums) {
    //     // 这里不能写成 0，如果否则 [-1] 这种数组输出是错的
    //     int result = Integer.MIN_VALUE;
    //     if (nums.length == 0) {
    //         return 0;
    //     }
    //     // i 不用小于 nums.length - 1
    //     // 如果写成 i < nums.length - 1 的话，数组最后一位单值最大的情况没法判断 [-1,23]
    //     // 还有只有一位的数组没法判断 [1]
    //     for (int i = 0; i < nums.length; i++) {
    //         int tmpValue = nums[i];
    //         if (result < tmpValue) {
    //             result = tmpValue;
    //         }
    //         // 当 i 等于数组最后一位时，j = i + 1 > nums.length，不会进入下面的循环
    //         for (int j = i + 1; j < nums.length; j++) {
    //             // 这是和，需要先判断单值最大的情况，比如 [-2,1]
    //             tmpValue += nums[j];
    //             if (result < tmpValue) {
    //                 result = tmpValue;
    //             }
    //         }
    //     }
    //     return result;
    // }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 避免单值最大的情况
        int result = getMax(nums);
        // 合并符号相同的值
        nums = mergeArr(nums);
        System.out.println(Arrays.toString(nums));

        // i 不用小于 nums.length - 1
        // 如果写成 i < nums.length - 1 的话，数组最后一位单值最大的情况没法判断 [-1,23]
        // 还有只有一位的数组没法判断 [1]
        for (int i = 0; i < nums.length; i++) {
            int tmpValue = nums[i];
            if (result < tmpValue) {
                result = tmpValue;
            }
            // 当 i 等于数组最后一位时，j = i + 1 > nums.length，不会进入下面的循环
            for (int j = i + 1; j < nums.length; j++) {
                // 这是和，需要先判断单值最大的情况，比如 [-2,1]
                tmpValue += nums[j];
                if (result < tmpValue) {
                    result = tmpValue;
                }
            }
        }
        return result;
    }

    /**
     * 获取数组中最大单值
     *
     * @param nums 数组
     * @return 最大单值
     */
    public static int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 合并数组
     * 将数组中连续的符号相同的数字合并
     *
     * @param nums 数组
     * @return 合并后的数组
     */
    public static int[] mergeArr(int[] nums) {
        //正负性标记，true 表示正数，false 表示负数
        boolean mark = nums[0] > 0;
        List<Integer> intList = new LinkedList<>();
        int sum = 0;
        for (int num : nums) {
            // 如果正负性相同，就累加
            if (num > 0 == mark) {
                sum += num;
                // 如果正负性不同，就将和加入到合并后的list中中
            } else {
                intList.add(sum);
                // 符号改变,需要改变 sum
                sum = num;
                // 改变正负性
                mark = num > 0;
            }
        }
        // 最后将 sum 加进去
        // 如果最后的正负性没变，加的是最后的和
        // 如果最后的正负性变了，加的是最后一个值
        intList.add(sum);
        return intList.stream().mapToInt(Integer::intValue).toArray();
    }
}
