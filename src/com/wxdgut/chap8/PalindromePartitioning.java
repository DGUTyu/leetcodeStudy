package com.wxdgut.chap8;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-24 21:34:55
 * <p>
 * 131. 分割回文串 难度：中等
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * <p>
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。
 * 返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例：
 * 输入：s = "aab"  输出：[["a","a","b"],["aa","b"]]
 * 输入：s = "a"    输出：[["a"]]
 * <p>
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */

public class PalindromePartitioning {
//    List<List<String>> res = new ArrayList<>();
//    Deque<String> deque = new LinkedList<>();
//
//    public List<List<String>> partition(String s) {
//        //方法1 回溯 9ms
//        backTracking(s, 0);
//        return res;
//    }
//
//    private void backTracking(String s, int startIndex) {
//        //如果起始位置大于s的大小，说明找到了一组分割方案
//        if (startIndex >= s.length()) {
//            res.add(new ArrayList(deque));
//            return;
//        }
//        for (int i = startIndex; i < s.length(); i++) {
//            //如果是回文子串，则记录
//            if (isPalindrome(s, startIndex, i)) {
//                String str = s.substring(startIndex, i + 1);
//                deque.addLast(str);
//            } else {
//                continue;
//            }
//            backTracking(s, i + 1); //起始位置后移，保证不重复
//            deque.removeLast();
//        }
//    }
//
//    private boolean isPalindrome(String s, int startIndex, int end) {
//        for (int i = startIndex, j = end; i < j; i++, j--) {
//            if (s.charAt(i) != s.charAt(j)) return false;
//        }
//        return true;
//    }
//    private boolean isPalindrome(String s, int left, int right) {
//        while(left<right && s.charAt(left) == s.charAt(right)) {
//            left++;
//            right--;
//        }
//        return left >= right;
//    }

    boolean[][] flag;
    List<List<String>> res = new ArrayList<>();
    List<String> list = new ArrayList<>();
    int n;

    public List<List<String>> partition(String s) {
        //方法2：回溯 + 动态规划预处理 6ms
        n = s.length();
        flag = new boolean[n][n]; //初始值为false
        //Arrays.fill 将指定的布尔值指定给指定布尔数组的每个元素。
        for (int i = 0; i < n; ++i) Arrays.fill(flag[i], true);
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                flag[i][j] = (s.charAt(i) == s.charAt(j)) && flag[i + 1][j - 1];
            }
        }
        dfs(s, 0);
        return res;
    }

    public void dfs(String s, int index) {
        if (index == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < n; ++i) {
            if (flag[index][i]) {
                list.add(s.substring(index, i + 1));
                dfs(s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

//    int[][] flag;
//    List<List<String>> res = new ArrayList<>();
//    List<String> list = new ArrayList<>();
//    int n;
//
//    public List<List<String>> partition(String s) {
//        //方法3：回溯 + 记忆化搜索 7ms
//        n = s.length();
//        flag = new int[n][n];
//        dfs(s, 0);
//        return res;
//    }
//
//    public void dfs(String s, int i) {
//        if (i == n) {
//            res.add(new ArrayList<String>(list));
//            return;
//        }
//        for (int j = i; j < n; ++j) {
//            if (isPalindrome(s, i, j) == 1) {
//                list.add(s.substring(i, j + 1));
//                dfs(s, j + 1);
//                list.remove(list.size() - 1);
//            }
//        }
//    }
//
//    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
//    public int isPalindrome(String s, int i, int j) {
//        if (flag[i][j] != 0) return flag[i][j];
//        if (i >= j) {
//            flag[i][j] = 1;
//        } else if (s.charAt(i) == s.charAt(j)) {
//            flag[i][j] = isPalindrome(s, i + 1, j - 1);
//        } else {
//            flag[i][j] = -1;
//        }
//        return flag[i][j];
//    }
}
