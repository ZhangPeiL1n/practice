package com.zpl.practice.algorithm.leetcode.maximumsubarray;

/**
 * 最大子数组和
 * 分治法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/11 15:41
 **/
public class MaxSubArrDivideConquer {

    public static void main(String[] args) {
        // int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(input));
        // System.out.println(1 / 2);
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        return maxSubArrDivide(nums, 0, nums.length - 1);
    }

    public static int maxSubArrDivide(int[] nums, int leftIndex, int rightIndex) {
        // 不知道要不要加一，老师给的这种判断方法，一个只有一个元素的的数组 [0]，左右index 都是0，长度是1
        int length = rightIndex - leftIndex + 1;
        // 递归到最后，数组只有一个元素，返回这个元素
        if (length == 1) {
            return nums[leftIndex];
        }
        int midIndex = (rightIndex - leftIndex) / 2 + leftIndex;

        int leftResult = maxSubArrDivide(nums, leftIndex, midIndex);
        //  避免mid被重复计算，这里 mid + 1，即将数组分为 [left,mid] 和 [mid + 1,right] 两部分
        int rightResult = maxSubArrDivide(nums, midIndex + 1, rightIndex);
        int midResult = maxSubArrMid(nums, leftIndex, rightIndex, midIndex);
        return Math.max(leftResult, Math.max(rightResult, midResult));
    }

    /**
     * 计算解在左右两个子数组当中的情况
     * 既然解在两个子数组当中，那么左数组的右确界必然是 mid
     * 同理右数组的左确界必然是 mid + 1
     * 合并两边和的最大值就是解
     *
     * @param nums       原数组
     * @param leftIndex  二分前数组的左确界
     * @param rightIndex 二分前数组的右确界
     * @param midIndex   数组二分的位置
     * @return 子数组和
     */
    public static int maxSubArrMid(int[] nums, int leftIndex, int rightIndex, int midIndex) {
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        // 计算解在左边的部分
        if (leftIndex < midIndex) {
            int sum = 0;
            for (int i = midIndex; i >= leftIndex; i--) {
                sum += nums[i];
                if (sum > leftMax) {
                    leftMax = sum;
                }
            }
        } else if (leftIndex == midIndex) {
            leftMax = nums[midIndex];
        }
        // 计算解在右边的部分
        // 课里说 midIndex == rightIndex 不可能出现，midIndex 必然是比 rightIndex 小的
        // 想了一下，在递归出口的地方校验住了 length 是 1 的数组元素只有一个的情况
        // 所以 (rightIndex - leftIndex) / 2 + leftIndex 是 leftIndex <= mid < rightIndex
        // 在计算时 i 因为要避免重复计算 midIndex 所以要设定为 midIndex + 1，因为整个数组被二分为 [left,mid] 和 [mid + 1,right]
        // 那么 midIndex + 1 就可能等于 rightIndex 了，所以循环里的条件就是 i <= rightIndex,保证 rightMax 最少能被 nums[mid+1] 覆盖掉
        // 即在判断 [-2,1] 这种情况时，mid = (rightIndex(1) - leftIndex(0)) / 2 + leftIndex(0) = 0
        // 此时 leftIndex = 0,midIndex = 0,rightIndex = 1
        if (midIndex < rightIndex) {
            int sum = 0;
            for (int i = midIndex + 1; i <= rightIndex; i++) {
                sum += nums[i];
                if (sum > rightMax) {
                    rightMax = sum;
                }
            }
        }
        return leftMax + rightMax;
    }
}
