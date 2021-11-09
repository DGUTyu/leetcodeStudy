package com.wxdgut.chap2;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-09 15:16:17
 *
 * 125. 验证回文串 难度：简单
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 *
 *给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *说明：本题中，我们将空字符串定义为有效的回文串。
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s){
        /**
         * 双指针 + Character.isLetterOrDigit + Character.toLowerCase
         * 时间复杂度 O(n)
         */
        //定义双指针
        int i=0,j=s.length()-1;
        //双指针遍历字符串,指针相遇则停止遍历
        while (i<j){
            //分别找到左右两边第一个是字母或数据的字符，不符条件就移动指针
            while(i<j && ! Character.isLetterOrDigit(s.charAt(i))) i++;
            while(i<j && ! Character.isLetterOrDigit(s.charAt(j))) j--;
            //找到首尾一对字符后，对单个字符统一大小写后进行比较
            //有一对不符合就return 结果,对比后移动左右指针
            if(Character.toLowerCase(s.charAt(i++))!= Character.toLowerCase(s.charAt(j--))){
                return false;
            }
        }
        //整个遍历对比过程中没有发现不符的情况
        return true;
    }
}