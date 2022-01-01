package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-01 23:10:34
 * <p>
 * 22. 括号生成 难度：中等
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * <p>
 * 数字 n 代表生成括号的对数，请你设计一个函数，
 * 用于能够生成所有可能的并且 有效的 括号组合。
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 * <p>
 * 示例：
 * 输入：n = 1 输出：["()"]
 * 输入：n = 3 输出：["((()))","(()())","(())()","()(())","()()()"]
 */

public class GenerateParentheses {
//    public List<String> generateParenthesis(int n) {
//        //方法1：递归
//        List<String> res = new ArrayList<>();
//        generate(0, 0, n, "", res);
//        return res;
//    }
//
//    private void generate(int left, int right, int n, String s, List<String> res) {
//        //递归终止条件
//        if (left == n && right == n) {
//            res.add(s);
//            return;
//        }
//        //当前层逻辑以及下探到下一层
//        //下面千万不能写成 --left和--right，因为回栈时该数值变了，会影响结果。
//        if (left < n) generate(left + 1, right, n, s + "(", res);
//        if (left > right) generate(left, right + 1, n, s + ")", res);
//    }

    public List<String> generateParenthesis(int n) {
        //方法2：dfs，与方法1类似
        List<String> res = new ArrayList<>();
        dfsA(n, n, "", res);
        return res;
    }

    private void dfsA(int left, int right, String s, List<String> res) {
        // 剪枝（左括号剩的多，即左括号没有右括号用的多时才剪枝，注意这个细节）
        //下面千万不能写成 --left和--right，因为回栈时该数值变了，会影响结果。
        if (left > right) return;
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) dfsA(left - 1, right, s + "(", res);
        if (right > 0) dfsA(left, right - 1, s + ")", res);
    }

//    public List<String> generateParenthesis(int n) {
//        //方法2的改进：使用StringBuilder  执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
//        List<String> res = new ArrayList<>();
//        dfsB(n, n, new StringBuilder(), res);
//        return res;
//    }
//
//    private void dfsB(int left, int right, StringBuilder s, List<String> res) {
//        if (left > right) return;
//        if (left == 0 && right == 0) {
//            res.add(s.toString());
//            return;
//        }
//        if (left > 0) {
//            dfsB(left - 1, right, s.append("("), res);
//            s.deleteCharAt(s.length() - 1);
//        }
//        if (right > 0) {
//            dfsB(left, right - 1, s.append(")"), res);
//            s.deleteCharAt(s.length() - 1);
//        }
//    }
}
