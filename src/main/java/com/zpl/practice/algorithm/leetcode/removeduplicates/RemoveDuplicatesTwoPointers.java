package com.zpl.practice.algorithm.leetcode.removeduplicates;

/**
 * 删除有序数组中的重复项
 * 双指针法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/10 11:01
 **/
public class RemoveDuplicatesTwoPointers {

    public static void main(String[] args) {
        int[] input = new int[]{0, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6};
        System.out.println(removeDuplicatesTwoPointers(input));
    }

    public static int removeDuplicatesTwoPointers(int[] nums) {
        int result = 0;

        if (nums.length == 0) {
            return result;
        }
        // 数组第一位是必然要放的，放在 nums[0]
        // 后面要使用 result 来充当第二个指针存放不一样的数字，所以 result 这里 result 要先 +1
        // 否则第二个不一样的数字会覆盖第一位（nums[0]）上
        result++;
        // 这里小于数组长度 -1，一般的数组遍历直接小于数组长度
        // 这里因为判断的是 i + 1，所以 应该是 i+1 < source.length
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                nums[result] = nums[i + 1];
                result++;
            }
        }
        return result;
    }
}
