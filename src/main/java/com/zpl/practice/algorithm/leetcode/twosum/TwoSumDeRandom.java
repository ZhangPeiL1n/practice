package com.zpl.practice.algorithm.leetcode.twosum;

import java.util.Arrays;

/**
 * 两数之和
 * 顺序求解
 *
 * @author ZhangPeiL1n
 * @date 2023/4/9 18:40
 **/
public class TwoSumDeRandom {
    public static void main(String[] args) {
        int[] inputArr = new int[]{3, 9, 2, 7, 11, 15};
        int target = 26;
        System.out.println(Arrays.toString(twoSum(inputArr, target)));
    }

    public static int[] twoSum(int[] source, int target) {
        int[] result = new int[2];
        for (int i = 0; i < source.length; i++) {
            for (int j = i + 1; j < source.length; j++) {
                if (target == source[i] + source[j]) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
