package com.zpl.practice.algorithm.leetcode.maxprofit;

/**
 * 买卖股票的最佳时机
 * 贪心法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/13 15:37
 **/
public class MaxProfitGreedy {
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        // 设第一天是最低买入价格
        int minPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 计算 今日卖出利润 = 今日价格 - 历史最低价
            int profit = prices[i] - minPrice;
            // 如果今日卖出利润还是负的，说明今日价格比历史最低买入价格还低，今日价格就是新的历史最低价格
            if (profit < 0) {
                minPrice = prices[i];
            } else {
                // 如果今日卖出利润比历史最高利润高，则今日卖出利润是新的历史最高利润
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}
