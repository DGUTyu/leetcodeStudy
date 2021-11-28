package com.wxdgut.chap5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-22 15:36:04
 * <p>
 * 242. 有效的字母异位词 难度：简单
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * 示例 :
 * 输入: s = "anagram", t = "nagaram" 输出: true
 * <p>
 * 提示：s 和 t 仅包含小写字母
 * <p>
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        /**
         * 方法1：排序
         * t 是 s 的异位词等价于「两个字符串排序后相等」
         * 时间复杂度：O(nlogn) 空间复杂度：O(logn)
         */
//        if (s.length() != t.length()) return false;
//        char[] str1 = s.toCharArray();
//        char[] str2 = t.toCharArray();
//        Arrays.sort(str1); //void
//        Arrays.sort(str2);
//        //注意不要写成 return str1.equals(str2);
//        return Arrays.equals(str1, str2);


        /**
         * 方法2：频次数组
         * 时间复杂度：O(n) 空间复杂度：O(S)其中S为字符集大小，此处 S=26
         * 维护一个长度为26的频次数组，先后遍历记录字符串 s / t 中字符出现的频次。
         * t是s的异位词等价于「两个字符串中字符出现的种类和次数均相等」。
         */
        if (s.length() != t.length()) return false;
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            arr[c - 'a']--;
            if (arr[c - 'a'] < 0) return false;
        }
        return true;

        /**
         * 进阶问题：
         *
         * 时间复杂度：O(n) 空间复杂度：O(S)
         *
         * UTF-8可以用来表示Unicode标准中的任何字符，
         * 而且其编码中的第一个字节仍与ASCII相容。
         * 进阶问题的核心点在于「字符是离散未知的」，因此我们用哈希表维护对应字符的频次即可。
         * 同时需要注意Unicode一个字符可能对应多个字节的问题，
         * 不同语言对于字符串读取处理的方式是不同的。
         *
         * Map.getOrDefault(Object key, V defaultValue)方法的作用是：
         * 当Map集合中有这个key时，就使用这个key值；如果没有就使用默认值defaultValue。
         */
//        if (s.length() != t.length()) return false;
//        Map<Character, Integer> map = new HashMap<>();
//        for (char c : s.toCharArray()) {
//            map.put(c, map.getOrDefault(c, 0) + 1);
//        }
//        for (char c : t.toCharArray()) {
//            map.put(c, map.getOrDefault(c, 0) - 1);
//            if (map.get(c) < 0) return false;
//        }
//        return true;
    }
}
