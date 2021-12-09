package com.wxdgut.chap7;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-09 21:27:57
 * <p>
 * 46. 全排列 难度：中等
 * 链接：https://leetcode-cn.com/problems/permutations
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。
 * 你可以 按任意顺序 返回答案。
 * <p>
 * 示例：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 */

public class Permutations {
//    //方法1：回溯版本1
//    public List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        backtrack(res, new ArrayList<>(), nums);
//        return res;
//    }
//
//    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
//        if (list.size() == nums.length) {
//            // 不可以写成 res.add(path);在 Java 中，参数传递是 值传递，
//            // 对象类型变量在传参的过程中，复制的是变量的地址。
//            // 这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此我们会看到 66 个空的列表对象。
//            // 解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
//            res.add(new ArrayList<>(list));
//        } else {
//            for (int i = 0; i < nums.length; i++) {
//                if (list.contains(nums[i])) continue;
//                list.add(nums[i]);
//                backtrack(res, list, nums);
//                list.remove(list.size() - 1);
//            }
//        }
//    }

//    //方法1：回溯版本2
//    public List<List<Integer>> permute(int[] nums) {
//        if (nums == null || nums.length == 0) return new ArrayList<>();
//        List<List<Integer>> res = new ArrayList<>();
//        backtrack(nums, res, new ArrayList<>(), new boolean[nums.length]);
//        return res;
//    }
//
//    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> cur, boolean[] used) {
//        if (cur.size() == nums.length) {
//            res.add(new ArrayList<>(cur));
//            return;
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (used[i]) continue;
//            cur.add(nums[i]);
//            used[i] = true;
//            backtrack(nums, res, cur, used);
//            used[i] = false;
//            cur.remove(cur.size() - 1);
//        }
//    }

    //方法1：回溯版本3
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(res, nums, new ArrayList<>(), used, 0);
        return res;
    }

    public void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> cur, boolean[] used, int len) {
        if (len == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) continue;
            cur.add(nums[i]);
            used[i] = true;
            backtrack(res, nums, cur, used, len + 1);
            used[i] = false;
            cur.remove(cur.size() - 1);
        }
    }

//    //方法1：回溯版本4
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> permute(int[] nums) {
//        helper(nums, 0, nums.length - 1);
//        return res;
//    }
//
//    public void helper(int[] nums, int start, int end) {
//        if (start == end) {
//            List<Integer> list = new ArrayList<>();
//            for (int i : nums) list.add(i);
//            res.add(list);
//        } else {
//            for (int i = start; i <= end; i++) {
//                int tempA = nums[i];
//                nums[i] = nums[start];
//                nums[start] = tempA;
//                helper(nums, start + 1, end);
//                int tempB = nums[i];
//                nums[i] = nums[start];
//                nums[start] = tempB;
//            }
//        }
//    }
}
