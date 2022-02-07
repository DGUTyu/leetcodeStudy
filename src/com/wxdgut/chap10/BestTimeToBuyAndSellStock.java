package com.wxdgut.chap10;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-02-07 09:56:04
 * <p>
 * 121. 买卖股票的最佳时机  难度：简单
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * 示例：
 * 输入：[7,1,5,3,6,4]  输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，
 * 最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 输入：prices = [7,6,4,3,1]  输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 注意此例子：输入：[2,4,1] 输出：2
 * <p>
 * 提示：
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */

public class BestTimeToBuyAndSellStock {
//    public int maxProfit(int[] prices) {
//        //方法1：暴力法 时间复杂度：O(N^2) 超时
//        int profit = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            for (int j = i + 1; j < prices.length; j++) {
//                if (prices[j] - prices[i] > profit) {
//                    profit = prices[j] - prices[i];
//                }
//            }
//        }
//        return profit;
//    }

//    public int maxProfit(int[] prices) {
//        //方法2：动态规划 版本1 时间复杂度：O(N)  3ms
//        //由于0 <= prices[i] <= 10^4，故不需要考虑[0,6,-3,7]这种情况
//        int maxCur = 0, maxSoFar = 0;
//        for (int i = 1; i < prices.length; i++) {
//            //一开始就负增长时，直接置零
//            maxCur = Math.max(0, maxCur += prices[i] - prices[i - 1]);
//            maxSoFar = Math.max(maxCur, maxSoFar);
//        }
//        return maxSoFar;
//    }

//    public int maxProfit(int[] prices) {
//        //方法2：动态规划 版本2 时间复杂度：O(N) 1ms
//        int profit = 0, bottom = Integer.MAX_VALUE;
//        for (int i : prices) {
//            bottom = Math.min(i, bottom);
//            profit = Math.max(i - bottom, profit);
//        }
//        return profit;
//    }

//    public int maxProfit(int[] prices) {
//        //方法3：贪心 版本1 时间复杂度：O(N) 1ms
//        int bottom = Integer.MAX_VALUE;
//        //int bottom = prices[0]; //最好这样写
//        int profit = 0;
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < bottom) bottom = prices[i]; //记录新的底部价格
//            else if (prices[i] - bottom > profit) {
//                //没有发现新的底部价格时，往后遍历，计算利润
//                profit = prices[i] - bottom;
//            }
//        }
//        return profit;
//    }

    public int maxProfit(int[] prices) {
        //方法3：贪心 版本2 时间复杂度：O(N) 1ms
        int profit = 0, bottom = Integer.MAX_VALUE;
        for (int i : prices) {
            if (i < bottom) bottom = i;
            else profit = Math.max(i - bottom, profit);
        }
        return profit;
    }
}
