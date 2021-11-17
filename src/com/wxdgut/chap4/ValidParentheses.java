package com.wxdgut.chap4;

import java.util.Stack;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-17 21:41:01
 * <p>
 * 20. 有效的括号 难度：简单
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，
 * 判断字符串是否有效。有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 * <p>
 * 示例 ：
 * 输入：s = "()[]{}" 输出：true
 * 输入：s = "([)]"   输出：false
 */

public class ValidParentheses {
    /**
     * 方法1：暴力解决
     * 首先括号成对出现，奇数的一定不符合。
     * 然后每次消去一个最小的完整括号，如果全部消除，说明是有效括号，
     * 如果replace之后字符串不发生变化且长度不为0，说明不是有效括号。
     *
     * 方法2：栈
     */

    public boolean isValid(String s) {
        //暴力版本1
//        int len = s.length();
//        if (len % 2 != 0) return false;
//        while (s.length() != 0) {
//            s = s.replace("{}", "")
//                    .replace("()", "")
//                    .replace("[]", "");
//            //如：([)] 无法替换时
//            if (len == s.length()) return false;
//            //备份一下替换后的s.length(), 以便下一次检查是否还可以替换
//            len = s.length();
//        }
//        return true;


        //暴力版本2
//        int len = s.length();
//        if (len % 2 != 0) return false;
//        do {
//            len = s.length();
//            s = s.replace("{}", "")
//                    .replace("()", "")
//                    .replace("[]", "");
//        } while (len != s.length());
//        return s.length() == 0;

        //栈版本1
        // 基本思想是每次遇到左括号时，将右括号")","]",或"}"压入栈中。
        // 如果字符串中出现右括号，我们需要检查堆栈是否为空，以及顶部元素是否与右括号相同。
        // 如果不是，则字符串无效。最后，我们还需要检查堆栈是否为空。
        // 但在实际情况下，此解决方案将不起作用，因为它的弹出机制将在任何字符、空格上触发。
        // 但根据题设的要求可以这样做。
//        Stack<Character> stack = new Stack<>();
//        for (char c : s.toCharArray()) {
//            if (c == '(') stack.push(')');
//            else if (c == '{') stack.push('}');
//            else if (c == '[') stack.push(']');
//            else if (stack.isEmpty() || stack.pop() != c) return false;
//        }
//        return stack.isEmpty();

        //栈版本2
        Stack<Character> stack = new Stack<>();
        String left = "({[";
        String right = ")}]";
        for (char c : s.toCharArray()) {
            if (left.indexOf(c) > -1) //c为 ( 或 { 或 [ 中的一个
                stack.push(c);
            else if (stack.isEmpty() || left.indexOf(stack.pop()) != right.indexOf(c))
                return false;
        }
        return stack.isEmpty();
    }
}
