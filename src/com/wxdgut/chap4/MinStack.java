package com.wxdgut.chap4;

import java.util.Stack;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-18 11:03:45
 * <p>
 * 155. 最小栈 难度：简单
 * 链接：https://leetcode-cn.com/problems/min-stack
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 提示：pop、top 和 getMin 操作总是在 非空栈 上调用。
 */

public class MinStack {
    //方法1：利用辅助栈
//    Stack<Integer> dataStack = new Stack<>();
//    Stack<Integer> minStack = new Stack<>();
//
//    public MinStack() {
//    }
//
//    public void push(int val) {
//        dataStack.push(val);
//        if (minStack.isEmpty() || val <= minStack.peek())
//            minStack.push(val);
//    }
//
//    public void pop() {
//        //integer 要用 equals 去比较
//        if (dataStack.peek().equals(minStack.peek()))
//            minStack.pop();
//        dataStack.pop();
//    }
//
//    public int top() {
//        return dataStack.peek();
//    }
//
//    public int getMin() {
//        return minStack.peek();
//    }

    //方法2：单栈
    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    public MinStack() {
    }

    public void push(int val) {
        if (min >= val) {
            // 以下两句不可互换。如果更新后再压栈，
            // 就会导致清空栈的最后两个元素时，由于stack.pop()==min成立，
            // min就为具体的某个数而不是Integer.MAX_VALUE，
            // 这会对后期的push操作有影响
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        //巧妙之处：条件成立时pop了两次，不成立时pop了一次
        if (stack.pop() == min) min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
