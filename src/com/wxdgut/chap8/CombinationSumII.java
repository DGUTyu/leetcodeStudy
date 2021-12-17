package com.wxdgut.chap8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-17 08:59:34
 * <p>
 * 40. 组合总和 II 难度：中等
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * <p>
 * 给定一个数组 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。 
 * <p>
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出: [[1,1,6],[1,2,5],[1,7],[2,6]]
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出: [[1,2,2],[5]]
 * <p>
 * 提示:
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */

public class CombinationSumII {
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        //方法1：回溯 3ms
//        Arrays.sort(candidates);
//        List<List<Integer>> res = new ArrayList<>();
//        backtrack(res, new ArrayList<>(), 0, candidates, target);
//        return res;
//    }
//
//    private void backtrack(List<List<Integer>> res, List<Integer> list, int start, int[] candidates, int target) {
//        if (target < 0) return;
//        else if (target == 0) res.add(new ArrayList<>(list));
//        else {
//            for (int i = start; i < candidates.length; i++) {
//                if (i > start && candidates[i] == candidates[i - 1]) continue;
//                list.add(candidates[i]);
//                // not i
//                backtrack(res, list, i + 1, candidates, target - candidates[i]);
//                list.remove(list.size() - 1);
//            }
//        }
//    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //方法1：回溯(剪枝) 2ms
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int start, int[] candidates, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 大剪枝：减去 candidates[i] 小于 0，则后面的 candidates[i + 1]等肯定也小于 0，因此用 break
            if (target - candidates[i] < 0) break;
            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            // not i
            backtrack(res, list, i + 1, candidates, target - candidates[i]);
            list.remove(list.size() - 1);
        }
    }
}
