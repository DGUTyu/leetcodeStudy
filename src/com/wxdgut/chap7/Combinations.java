package com.wxdgut.chap7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-08 21:37:08
 * <p>
 * 77. 组合 难度：中等
 * 链接：https://leetcode-cn.com/problems/combinations
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例：
 * 输入：n = 4, k = 2
 * 输出：[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4],]
 */

public class Combinations {
//    //方法1：递归 1ms
//    List<Integer> list = new ArrayList<Integer>();
//    List<List<Integer>> res = new ArrayList<List<Integer>>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        dfs(1, n, k);
//        return res;
//    }
//
//    public void dfs(int cur, int n, int k) {
//        // 剪枝：list 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 list
//        if (list.size() + (n - cur + 1) < k) return;
//        // 记录合法的答案
//        if (list.size() == k) {
//            res.add(new ArrayList<Integer>(list));
//            return;
//        }
//        // 考虑选择当前位置
//        list.add(cur);
//        dfs(cur + 1, n, k);
//        list.remove(list.size() - 1);
//        // 考虑不选择当前位置
//        dfs(cur + 1, n, k);
//    }

//    //方法2：非递归（字典序法） 6ms
//    public List<List<Integer>> combine(int n, int k) {
//        List<Integer> list = new ArrayList<>();
//        List<List<Integer>> res = new ArrayList<>();
//        // 初始化 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
//        // 末尾加一位 n + 1 作为哨兵
//        for (int i = 1; i <= k; ++i) list.add(i);
//        list.add(n + 1);
//        int j = 0;
//        while (j < k) {
//            res.add(new ArrayList<Integer>(list.subList(0, k)));
//            j = 0;
//            // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
//            // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
//            while (j < k && list.get(j) + 1 == list.get(j + 1)) {
//                list.set(j, j + 1);
//                ++j;
//            }
//            // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
//            list.set(j, list.get(j) + 1);
//        }
//        return res;
//    }

    //方法3：回溯版本1 1ms
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    public void combine(List<List<Integer>> res, List<Integer> list, int start, int n, int k) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // i <= end -k + 1, don't use i <=n, it will save a lot of time.
        //for (int i = start; i <= n; i++) {
        for (int i = start; i <= n - k + 1; i++) {
            list.add(i);
            combine(res, list, i + 1, n, k - 1);
            list.remove(list.size() - 1);
        }
    }

//    //方法3：回溯版本2 1ms
//    List<List<Integer>> res = new ArrayList<>();
//    LinkedList<Integer> list = new LinkedList<>();
//
//    public List<List<Integer>> combine(int n, int k) {
//        combine(n, k, 1);
//        return res;
//    }
//
//    private void combine(int n, int k, int startIndex) {
//        if (list.size() == k) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        for (int i = startIndex; i <= n - (k - list.size()) + 1; i++) {
//            list.add(i);
//            combine(n, k, i + 1);
//            list.removeLast();
//        }
//    }

//    //方法4：迭代 6ms
//    public List<List<Integer>> combine(int n, int k) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (k == 0) {
//            res.add(new ArrayList<>());
//            return res;
//        }
//        List<Integer> list = new ArrayList<>(k + 1);
//        for (int i = 1; i <= k; i++) {
//            list.add(i);
//        }
//        list.add(n + 1);
//        int i = 0;
//        while (i < k) {
//            res.add(new ArrayList<>(list.subList(0, k)));
//            i = 0;
//            while (i < k && (list.get(i) + 1 == list.get(i + 1))) {
//                list.set(i, i + 1);
//                i++;
//            }
//            list.set(i, list.get(i) + 1);
//        }
//        return res;
//    }
}

