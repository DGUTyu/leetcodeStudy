package com.wxdgut.chap4;

import java.util.Stack;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-21 17:22:40
 * <p>
 * 42. 接雨水 难度：困难
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
 * 计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]  输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
 * 在这种情况下，可以接 6 个单位的雨水。
 * <p>
 * height = [4,2,0,3,2,5]  输出：9
 */

public class TrappingRainWater {
    //    public int trap(int[] height) {
//        /**
//         * 方法1：动态规划 1ms
//         * 时间复杂度：O(n) 空间复杂度：O(n)
//         * 在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。
//         * 但是我们可以提前存储这个值。因此，可以通过动态编程解决。
//         * 找到数组中从下标 i=1 开始从左往右每次两两比较得出的最高条形块高度left_max
//         * 找到数组中从下标 i=size - 1 开始从右往左每次两两比较得出的最高条形块高度right_max
//         * 每列的容水量 = Math.min(left_max[i], right_max[i]) - height[i]
//         *
//         * height    [0,1,0,2,1,0,1,3,2,1,2,1]
//         * left_max  [0,1,1,2,2,2,2,3,3,3,3,3]
//         * right_max [3,3,3,3,3,3,3,3,2,2,2,1]
//         */
//        if (height == null || height.length == 0) return 0;
//        int ans = 0;
//        int size = height.length;
//        int[] left_max = new int[size];
//        int[] right_max = new int[size];
//        left_max[0] = height[0];
//        right_max[size - 1] = height[size - 1];
//        for (int i = 1; i < size; i++) {
//            left_max[i] = Math.max(height[i], left_max[i - 1]);
//        }
//        for (int i = size - 2; i >= 0; i--) {
//            right_max[i] = Math.max(height[i], right_max[i + 1]);
//        }
//        for (int i = 1; i < size - 1; i++) {
//            ans += Math.min(left_max[i], right_max[i]) - height[i];
//        }
//        return ans;
//    }

//    public int trap(int[] height) {
//        /**
//         * 方法2：单调栈 2ms
//         * 时间复杂度：O(n) 空间复杂度：O(n)
//         * 我们可以不用像动态规划那样存储最大高度，而是用栈来跟踪可能储水的最长的条形块。
//         * 在遍历数组时维护一个栈，使用栈就可以在一次遍历内完成计算。
//         * 如果当前的条形块小于或等于栈顶的条形块，将条形块的索引入栈，
//         * 即当前的条形块被栈中的前一个条形块界定。
//         * 如果发现一个条形块长于栈顶，则可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
//         * 弹出栈顶元素并且累加答案到 ans
//         */
//        if (height == null) return 0;
//        Stack<Integer> stack = new Stack<>();
//        int i = 0, ans = 0;
//        while (i < height.length) {
//            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
//                stack.push(i++); //维护一个单调栈，存的是元素下标
//            } else {
//                int top = stack.pop();
//                if (stack.isEmpty()) continue;
//                int distance = i - stack.peek() - 1;
//                int bound_height = Math.min(height[stack.peek()], height[i]) - height[top];
//                ans += distance * bound_height;
//            }
//        }
//        return ans;
//    }

//    public int trap(int[] height) {
//        /**
//         * 方法3：双指针（指针对撞） 0ms
//         * 时间复杂度：O(n) 空间复杂度：O(n)
//         * 和方法1 相比，我们不从左和从右分开计算，我们想办法一次完成遍历。
//         * 每一次 i 和 j 指针向中间移动的过程,都能确定一个位置的存水量。
//         */
//        int i = 0;
//        int j = height.length - 1;
//        int ans = 0;
//        int left_max = 0;
//        int right_max = 0;
//        while (i <= j) {
//            left_max = Math.max(left_max, height[i]);
//            right_max = Math.max(right_max, height[j]);
//            if (left_max < right_max) {
//                ans += (left_max - height[i]);
//                i++;
//            } else {
//                ans += (right_max - height[j]);
//                j--;
//            }
//        }
//        return ans;
//    }

    public int trap(int[] height) {
        //方法4：方法3的精简
        int i = 0;
        int j = height.length - 1;
        int ans = 0;
        int left_max = 0;
        int right_max = 0;
        while (i <= j) {
            left_max = Math.max(left_max, height[i]);
            right_max = Math.max(right_max, height[j]);
            if (left_max < right_max)ans += (left_max - height[i++]);
            else ans += (right_max - height[j--]);
        }
        return ans;
    }
}
