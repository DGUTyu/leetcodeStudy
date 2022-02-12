package com.wxdgut.chap10;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-02-12 09:47:50
 * <p>
 * 45. 跳跃游戏 II  难度：中等
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是  使用最少的跳跃次数  到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 示例:
 * 输入: nums = [2,3,1,1,4]  输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 输入: nums = [2,3,0,1,4]  输出: 2
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 */

public class JumpGameII {
//    public int jump(int[] nums) {
//        //方法1：顺藤摸瓜  时间复杂度：O（N^2） 11ms
//        // 我们知道最终要到达最后一个位置，
//        // 然后我们找前一个位置，遍历数组，找到能到达它的位置，离它最远的就是要找的位置。
//        // 然后继续找上上个位置，最后到了第 0 个位置就结束了。
//        // 至于离它最远的位置，其实我们从左到右遍历数组，第一个满足的位置就是我们要找的。
//        int position = nums.length - 1; //要找的位置
//        int steps = 0;
//        while (position != 0) { //是否到了第 0 个位置
//            for (int i = 0; i < position; i++) {
//                if (nums[i] >= position - i) {
//                    position = i; //更新要找的位置
//                    steps++;
//                    break;
//                }
//            }
//        }
//        return steps;
//    }

    public int jump(int[] nums) {
        //方法2：贪心 时间复杂度：O（n） 1ms
        //我们每次在可跳范围内选择可以那些可以跳的更远的位置。
        //这里要注意一个细节，就是 for 循环中，i < nums.length - 1，少了末尾。
        // 因为开始的时候边界是第 0 个位置，steps 已经加 1 了。
        // 如果最后一步刚好跳到了末尾，此时 steps 其实不用加 1 了。
        // 如果是 i < nums.length，i 遍历到最后的时候，
        // 会进入 if 语句中，steps 会多加 1 。
        int currEnd = 0, maxReachIndex = 0, step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxReachIndex = Math.max(maxReachIndex, i + nums[i]); //找能跳的最远的
            if (i == currEnd) { //遇到边界，就更新边界，并且步数加一
                currEnd = maxReachIndex;
                step++;
            }
        }
        return step;
    }

//    public int jump(int[] nums) {
//        //方法3：BFS 1ms
//        int len = nums.length;
//        if (len < 2) return 0;
//        int level = 0, currMax = 0, nextMax = 0, i = 0;
//        while (i <= currMax) {
//            level++;
//            for (; i <= currMax; i++) {
//                nextMax = Math.max(nextMax, i + nums[i]);
//                if (nextMax >= len - 1) return level;
//            }
//            currMax = nextMax;
//        }
//        return 0;
//    }
}
