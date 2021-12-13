package com.wxdgut.chap7;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-13 21:17:16
 * <p>
 * 47. 全排列 II  难度：中等
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * <p>
 * 给定一个 可包含重复数字 的序列nums ，按任意顺序返回所有不重复的全排列。
 * <p>
 * 示例：
 * 输入：nums = [1,1,2]
 * 输出：[[1,1,2],[1,2,1],[2,1,1]]
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */

public class PermutationsII {
    //方法1：回溯 1ms
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums); //排序
        backtrack(res, new ArrayList<>(), nums, used);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue; //剪枝
            list.add(nums[i]);
            used[i] = true;
            backtrack(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

//    //方法1：回溯 + deque 1ms
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        int len = nums.length;
//        List<List<Integer>> res = new ArrayList<>();
//        if (len == 0) return res;
//        // 排序（升序或者降序都可以），排序是剪枝的前提
//        Arrays.sort(nums);
//        boolean[] used = new boolean[len];
//        Deque<Integer> deque = new ArrayDeque<>(len);
//        dfs(nums, len, 0, used, deque, res);
//        return res;
//    }
//
//    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> deque, List<List<Integer>> res) {
//        if (depth == len) {
//            res.add(new ArrayList<>(deque));
//            return;
//        }
//        for (int i = 0; i < len; ++i) {
//            if (used[i]) continue;
//            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
//            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
//            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
//            deque.addLast(nums[i]);
//            used[i] = true;
//            dfs(nums, len, depth + 1, used, deque, res);
//            // 回溯部分的代码，和 dfs 之前的代码是对称的
//            used[i] = false;
//            deque.removeLast();
//        }
//    }

//    //方法1：回溯 + deque 改版 2ms
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (nums.length == 0) return res;
//        Arrays.sort(nums);
//        boolean[] used = new boolean[nums.length];
//        Deque<Integer> deque = new ArrayDeque<>(nums.length);
//        dfs(res, deque, nums, used);
//        return res;
//    }
//
//    private void dfs(List<List<Integer>> res, Deque<Integer> deque, int[] nums, boolean[] used) {
//        if (deque.size() == nums.length) {
//            res.add(new ArrayList<>(new ArrayList<>(deque)));
//            return;
//        }
//        for (int i = 0; i < nums.length; ++i) {
//            if (used[i]) continue;
//            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
//            deque.addLast(nums[i]);
//            used[i] = true;
//            dfs(res, deque, nums, used);
//            used[i] = false;
//            deque.removeLast();
//        }
//    }
}
