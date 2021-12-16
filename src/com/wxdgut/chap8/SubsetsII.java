package com.wxdgut.chap8;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-16 17:04:42
 * <p>
 * 90. 子集 II 难度：中等
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * <p>
 * 给你一个整数数组 nums ，其中可能包含重复元素，
 * 请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * 示例：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 */

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //方法1：回溯
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, ArrayList<Integer> list, int start, int[] nums) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            //不可写成backtrack(res, list, start + 1, nums);
            backtrack(res, list, i + 1, nums);
            list.remove(list.size() - 1);
        }
    }

//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        //方法2：递归
//        Arrays.sort(nums);
//        List<List<Integer>> res = new ArrayList<>();
//        dfs(res, new ArrayList<>(), 0, nums, true);
//        return res;
//    }
//
//    private void dfs(List<List<Integer>> res, ArrayList<Integer> list, int index, int[] nums, boolean isPicked) {
//        if (index == nums.length) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        //不选这个数
//        dfs(res, list, index + 1, nums, false);
//        //检查是否重复
//        if (index > 0 && nums[index - 1] == nums[index] && isPicked == false) {
//            return;
//        }
//        //选这个数
//        list.add(nums[index]);
//        dfs(res, list, index + 1, nums, true);
//        //逆转当前状态
//        list.remove(list.size() - 1);
//    }
}
