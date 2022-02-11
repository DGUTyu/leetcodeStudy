package com.wxdgut.chap10;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-02-11 15:55:46
 * <p>
 * 55. 跳跃游戏  难度：中等
 * 链接：https://leetcode-cn.com/problems/jump-game
 * <p>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例：
 * 输入：nums = [2,3,1,1,4]  输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 输入：nums = [3,2,1,0,4]  输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ，
 * 所以永远不可能到达最后一个下标。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 */

public class JumpGame {
//    public boolean canJump(int[] nums) {
//        //方法1：迭代更新能达到的最大索引  版本1   2ms
//        int len = nums.length;
//        int index = 0;
//        for (int reach = 0; index < len && index <= reach; ++index)
//            reach = Math.max(index + nums[index], reach);
//        return index == len; //是否走到底,因为index结束循环后加了1，故用len
//    }

//    public boolean canJump(int[] nums) {
//        //方法1：迭代更新能达到的最大索引  版本2  2ms
//        int reach = 0;
//        for (int i = 0; i <= reach; i++) {
//            reach = Math.max(reach, i + nums[i]);
//            if (reach >= nums.length - 1) return true;
//        }
//        return false;
//    }

    public boolean canJump(int[] nums) {
        //方法2：方法1的反向工作  1ms
        //从上一个索引开始反向工作。
        //跟踪可以跳转到最后一个索引的最小索引，检查当前索引是否可以跳到此最小索引。
        int len = nums.length;
        int last = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last == 0;
    }

}
