package com.zpl.practice.algorithm.leetcode.removeduplicates;

import java.util.BitSet;

/**
 * 删除有序数组中的重复项
 * 位标记法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/10 11:01
 **/
public class RemoveDuplicatesBitSet {

    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6};
        System.out.println(removeDuplicatesBitSet(input));
    }

    public static int removeDuplicatesBitSet(int[] nums) {
        int result = 0;

        if (nums.length == 0) {
            return result;
        }
        // 用于位标记
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        result++;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                bitSet.set(i + 1);
            }
        }
        for (int i = 1; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                nums[result] = nums[i];
                result++;
            }
        }
        return result;
    }
}
