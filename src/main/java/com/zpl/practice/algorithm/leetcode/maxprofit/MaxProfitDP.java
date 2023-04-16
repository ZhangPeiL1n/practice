package com.zpl.practice.algorithm.leetcode.maxprofit;

/**
 * 买卖股票的最佳时机
 * 动态规划
 *
 * @author ZhangPeiL1n
 * @date 2023/4/13 16:36
 **/
public class MaxProfitDP {
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        // 前 i 天的历史最低价格
        int[] buyIn = new int[prices.length];
        // 前 i 天的最高利润
        int[] saleOut = new int[prices.length];
        buyIn[0] = prices[0];
        saleOut[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            buyIn[i] = Math.min(buyIn[i - 1], prices[i]);
            saleOut[i] = Math.max(saleOut[i - 1], prices[i] - buyIn[i - 1]);
        }
        return saleOut[prices.length - 1];
    }
}
