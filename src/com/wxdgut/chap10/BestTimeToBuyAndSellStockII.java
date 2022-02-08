package com.wxdgut.chap10;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-02-08 10:20:03
 * <p>
 * 122. 买卖股票的最佳时机 II  难度：中等
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * <p>
 * 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 * 在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。
 * 你也可以购买它，然后在 同一天 出售。返回 你能获得的 最大 利润 。
 * <p>
 * 示例:
 * 输入: prices = [7,1,5,3,6,4]  输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出,
 * 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 输入: prices = [1,2,3,4,5]  输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出,
 * 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 输入: prices = [7,6,4,3,1]  输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */

public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        //方法1：贪心 版本1 时间复杂度 O(N) 1ms
        //贪心算法的直觉：由于不限制交易次数，只要今天股价比昨天高，就交易
        //由于股票的购买没有限制，因此整个问题等价于寻找 x 个不相交的区间使得收益最大化
        //贪心的角度考虑我们每次选择贡献大于 0 的区间即能使得答案最大化
        //需要说明的是，贪心算法只能用于计算最大利润，计算的过程并不是实际的交易过程
        //如例子 [1,2,3,4,5]，ans=4,
        //实际的交易并不是进行 4 次买入和 4 次卖出，而是在第 1 天买入，第 5 天卖出。
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0); //写法1
            //if (prices[i] - prices[i - 1] > 0) res += prices[i] - prices[i - 1]; //写法2
        }
        return res;
    }

//    public int maxProfit(int[] prices) {
//        //方法1：贪心 版本2 时间复杂度 O(N) 0ms
//        int len = prices.length;
//        int res = 0;
//        int max = prices[len - 1];
//        for (int i = len - 2; i >= 0; i--) {
//            if (prices[i] < max) res += max - prices[i];
//            max = prices[i];
//        }
//        return res;
//    }

}
