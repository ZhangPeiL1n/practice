package com.zpl.practice.algorithm.leetcode.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 哈希表解法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/9 21:39
 **/
public class TwoSumN {

    public static void main(String[] args) {
        int[] inputArr = new int[]{3, 2, 4};
        int target = 6;

        System.out.println(Arrays.toString(twoSum(inputArr, target)));
    }

    public static int[] twoSum(int[] source, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < source.length; i++) {
            map.put(source[i], i);
        }
        for (int i = 0; i < source.length; i++) {
            if (map.containsKey(target - source[i])) {
                 // 下标不能重复
                 if (i != map.get(target - source[i])) {
                    result[0] = i;
                    result[1] = map.get(target - source[i]);
                    return result;
                }
            }
        }
        return result;
    }
}
