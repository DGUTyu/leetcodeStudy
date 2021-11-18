package com.wxdgut.chap4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-18 19:25:19
 * <p>
 * 84. 柱状图中最大的矩形 难度：困难
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例 1:
 * 输入：heights = [2,1,5,6,2,3] 输出：10 解释：最大的矩形面积为 10
 * 输入： heights = [2,4]        输出： 4 解释：最大的矩形面积为 4
 */

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        //方法1：暴力 （超时） 时间复杂度：O(N^2) 空间复杂度：O(1)
//        int len = heights.length;
//        if (len == 0) return 0;
//        if (len == 1) return heights[0];
//        int res = 0;
//        for (int i = 0; i < len; i++) {
//            //找左边最后 1 个大于等于 heights[i] 的下标
//            int left = i;
//            int curHeight = heights[i];
//            while (left > 0 && heights[left - 1] >= curHeight) {
//                left--;
//            }
//            //找右边最后 1 个大于等于 heights[i] 的索引
//            int right = i;
//            while (right < len - 1 && heights[right + 1] >= curHeight) {
//                right++;
//            }
//            //拿左边和右边比自己高（或相等）的下标相减 + 1
//            int width = right - left + 1;
//            res = Math.max(res, width * curHeight);
//        }
//        return res;

        //方法2：双端队列 + 哨兵 时间复杂度：O(N) 空间复杂度：O(N)
        //思路：
        // 1.位置一个单调递增栈，存储下标
        // 2.计算：当出现小于栈顶元素时，开始更新最大值
        // 3.将当前元素入栈
//        int len = heights.length;
//        if (len == 0) return 0;
//        if (len == 1) return heights[0];
//        int res = 0;
//        int[] newHeights = new int[len + 2];
//        newHeights[0] = 0;
//        System.arraycopy(heights, 0, newHeights, 1, len);
//        newHeights[len + 1] = 0;
//        len += 2;
//        heights = newHeights; //更新数组引用，避免修改主要逻辑
//        Deque<Integer> stack = new ArrayDeque<>(len);
//        // 先放入哨兵，在循环里就不用做非空判断
//        stack.addLast(0);
//
//        for (int i = 1; i < len; i++) {
//            while (heights[i] < heights[stack.peekLast()]) {
//                int curHeight = heights[stack.pollLast()]; //出栈
//                //右边界 - 左边界 - 1
//                int curWidth = i - stack.peekLast() - 1;
//                res = Math.max(res, curHeight * curWidth);
//            }
//            stack.addLast(i);
//        }
//        return res;


        //方法3：方法2的改进
//        int max = 0;
//        //也可以用 Stack<Integer> stack = new Stack<>();
//        //建议用Deque，因为Deque替换Stack后内存提高了一点但时间降了
//        Deque<Integer> stack = new LinkedList<>();
//        int[] nums = new int[heights.length + 2];
//        for (int i = 1; i < heights.length + 1; i++) {
//            nums[i] = heights[i - 1];
//        }
//        for (int i = 0; i < nums.length; i++) {
//            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
//                int H = nums[stack.pop()];
//                int W = i - stack.peek() - 1;
//                max = Math.max(max, H * W);
//            }
//            stack.push(i);
//        }
//        return max;

        //方法4：方法3的改进,利用数组替换stack
        int max = 0;
        int[] nums = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            nums[i] = heights[i - 1];
        }
        int[] stk = new int[heights.length + 2];
        int top = -1;
        for (int i = 0; i < nums.length; i++) {
            while (top >= 0 && nums[stk[top]] > nums[i]) {
                int left = stk[top--];
                int W = i - stk[top] - 1;
                int H = nums[left];
                max = Math.max(max, H * W);
            }
            stk[++top] = i;
        }
        return max;
    }
}
