package com.wxdgut.chap8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-16 10:58:50
 * <p>
 * 78. 子集 难度：中等
 * 链接：https://leetcode-cn.com/problems/subsets
 * <p>
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。
 * 返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        //方法1：回溯
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, ArrayList<Integer> list, int start, int[] nums) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            //不可写成backtrack(res, list, start + 1, nums);
            backtrack(res, list, i + 1, nums);
            list.remove(list.size() - 1);
        }
    }

//    public List<List<Integer>> subsets(int[] nums) {
//        //方法2：递归
//        List<List<Integer>> res = new ArrayList<>();
//        boolean[] used = new boolean[nums.length];
//        dfs(res, new ArrayList<>(), 0, nums);
//        return res;
//    }

//    private void dfs(List<List<Integer>> res, ArrayList<Integer> list, int level, int[] nums) {
//        if (level == nums.length) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        //不选这个数
//        dfs(res, list, level + 1, nums);
//        //选这个数
//        list.add(nums[level]);
//        dfs(res, list, level + 1, nums);
//        //逆转当前状态
//        list.remove(list.size() - 1);
//    }

//    public List<List<Integer>> subsets(int[] nums) {
//        //方法3：DP
//        List<List<Integer>> res = new ArrayList<>();
//        res.add(new ArrayList<>());
//        for (int n : nums) {
//            int size = res.size();
//            for (int i = 0; i < size; i++) {
//                //拷贝一份list,用于添加 n
//                List<Integer> list = new ArrayList<>(res.get(i));
//                list.add(n);
//                res.add(list);
//            }
//        }
//        return res;
//    }
}
