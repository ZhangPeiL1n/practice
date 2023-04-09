package com.zpl.practice.algorithm.search;

/**
 * 二分查找法 - 非递归
 *
 * @author ZhangPeiL1n
 * @date 2023/4/9 21:04
 **/
public class BinarySearchNonRecursion {
    public static int binarySearch(int[] source, int target) {
        int result = -1;

        int leftIndex = 0;
        int rightIndex = source.length - 1;
        while (leftIndex <= rightIndex) {
            // 注意这里要加 leftIndex，不加左边界值计算的实际上是左边界的偏移量
            // 右边界 - 左边界 = offset，但是起始位置是左边界，否则在计算大于 target 的值时左边界值不对
            int mid = (rightIndex - leftIndex) / 2 + leftIndex;
            if (target == source[mid]) {
                return mid;
            } else if (target < source[mid]) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(source, 6));
    }
}
