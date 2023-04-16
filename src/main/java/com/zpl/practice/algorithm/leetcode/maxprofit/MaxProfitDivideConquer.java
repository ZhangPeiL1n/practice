package com.zpl.practice.algorithm.leetcode.maxprofit;

/**
 * 买卖股票的最佳时机
 * 分治法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/13 17:39
 **/
public class MaxProfitDivideConquer {


    public static void main(String[] args) {
        // int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int[] prices = new int[]{5, 2, 3, 0, 3, 5, 6, 8, 1, 5};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int[] divide = divide(prices, 0, prices.length - 1);
        return divide[0];
    }

    /**
     * 处理买入在左数组，卖出在又数组的情况
     *
     * @param leftMinPrice  左数组的解
     * @param rightMaxPrice 右数组的解
     * @return 子问题三的解
     */
    public static int[] handleMidResult(int leftMinPrice, int rightMaxPrice) {
        int[] midResult = new int[3];
        midResult[0] = rightMaxPrice - leftMinPrice;
        midResult[1] = leftMinPrice;
        midResult[2] = rightMaxPrice;
        return midResult;
    }

    /**
     * 合并子问题的解
     *
     * @param leftResult  左数组的解
     * @param rightResult 右数组的解
     * @param midResult   解在左右两边的情况
     * @return 子问题的解
     */
    public static int[] handleResult(int[] leftResult, int[] rightResult, int[] midResult) {
        int[] result = new int[3];
        result[0] = Math.max(leftResult[0], Math.max(rightResult[0], midResult[0]));
        result[1] = Math.min(leftResult[1], Math.min(rightResult[1], midResult[1]));
        result[2] = Math.max(leftResult[2], Math.max(rightResult[2], midResult[2]));
        return result;
    }


    public static int[] divide(int[] prices, int left, int right) {
        int length = right - left + 1;
        // 存放解的数组，因为解中不仅需要最大利润，还需要这部分解中的最低价格和最高价格
        // 所以 result[0] 存放最大利润
        // result[1] 存放最低价格
        // result[2] 存放最高价格
        int[] result;

        // 递归出口
        if (length <= 3) {
            result = new int[3];
            // 最大利润
            int maxProfit = 0;
            // 最小买入价格
            int minPrice = prices[left];
            // 最大卖出价格
            int maxPrice = prices[left];
            for (int i = left + 1; i <= right; i++) {
                // 当前卖出利润与历史最高利润比较
                maxProfit = Math.max(prices[i] - minPrice, maxProfit);
                // 最大价格
                maxPrice = Math.max(prices[i], maxPrice);
                // 当前价格和历史最低买入
                minPrice = Math.min(prices[i], minPrice);
            }
            result[0] = maxProfit;
            result[1] = minPrice;
            result[2] = maxPrice;
            return result;
        }

        int mid = (right - left) / 2 + left;
        int[] leftResult = divide(prices, left, mid);
        int[] rightResult = divide(prices, mid + 1, right);
        // 子问题三
        int[] midResult = handleMidResult(leftResult[1], rightResult[2]);

        // 合并子问题
        result = handleResult(leftResult, rightResult, midResult);
        return result;
    }
}
