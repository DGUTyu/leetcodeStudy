package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-16 22:28:26
 * <p>
 * 66. 加一 难度：简单
 * 链接：https://leetcode-cn.com/problems/plus-one
 * <p>
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * 输入：digits = [1,2,3]  解释：输入数组表示数字 123。
 * 输出：[1,2,4]           解释：输出数组表示数字 124。
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
        //参考1：
//        for (int i = digits.length-1; i >=0; i--) {
//            if(digits[i]!=9) {
//                digits[i]++;
//                return digits;
//            }
//            digits[i]=0;
//        }
//        //digits 中所有的元素均为 9，需要扩展数组长度
//        int[] ans=new int[digits.length+1];
//        ans[0]=1;
//        return ans;

        //参考2：
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            if (digits[i] <= 9) return digits;
            digits[i] = 0;
        }
        //digits 中所有的元素均为 9，需要扩展数组长度
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }
}
