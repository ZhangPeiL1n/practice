package com.zpl.practice.algorithm.leetcode.maxprofit;

/**
 * 买卖股票的最佳时机
 * 规约法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/13 15:05
 **/
public class MaxProfit {

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int[] profitDaily = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            profitDaily[i - 1] = prices[i] - prices[i - 1];
        }
        return maxSubArrGreedy(profitDaily);
    }

    public static int maxSubArrGreedy(int[] profitDaily) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int profit : profitDaily) {
            sum += profit;
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
