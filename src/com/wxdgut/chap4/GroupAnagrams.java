package com.wxdgut.chap4;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-22 16:21:15
 * <p>
 * 49. 字母异位词分组 难度：中等
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * <p>
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 * <p>
 * 示例 :
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 输入: strs = [""]    输出: [[""]]
 * 输入: strs = ["a"]   输出: [["a"]]
 * <p>
 * 提示：strs[i] 仅包含小写字母
 */

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        /**
         * 方法1：排序
         * 时间复杂度：O(nklogk) 空间复杂度：O(nk)
         *
         * 其中n是strs中的字符串的数量，k是strs中的字符串的的最大长度。
         * 由于互为字母异位词的两个字符串包含的字母相同，
         * 因此对两个字符串分别进行排序之后得到的字符串一定是相同的,
         * 故可以将排序之后的字符串作为哈希表的键。
         *
         * 模式识别:一旦需要根据特征进行归类,就应该利用散列表
         */
//        Map<String, List<String>> map = new HashMap<>();
//        for (String s : strs) {
//            char[] charArray = s.toCharArray();
//            Arrays.sort(charArray);
//            String key = new String(charArray);
//            List<String> list = map.getOrDefault(key, new ArrayList<String>());
//            list.add(s);
//            ////一个Map中不能包含相同的key，每个key只能映射一个value即list，就自动覆盖了
//            map.put(key, list);
//        }
//        //new ArrayList()构造一个包含指定元素的列表集合，按集合的迭代器。
//        return new ArrayList<List<String>>(map.values());

        //方法1的另一版本
//        if (strs == null || strs.length == 0) return new ArrayList<>();
//        Map<String, List<String>> map = new HashMap<>();
//        for (String s : strs) {
//            char[] charArray = s.toCharArray();
//            Arrays.sort(charArray);
//            String key = String.valueOf(charArray);//new String(charArray)
//            // map.getOrDefault(key,new ArrayList<>()).add(s);
//            // 不能用上面那个，因为没有put，
//            // 我们只是获取到列表然后在列表后面添加而已，没有修改哈希表的值
//            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
//            map.get(key).add(s);
//        }
//        return new ArrayList<List<String>>(map.values());

        /**
         * 方法2：计数
         * 时间复杂度：O(nk) 空间复杂度：O(nk)
         *
         * 由于互为字母异位词的两个字符串包含的字母相同，
         * 因此两个字符串中的相同字母出现的次数一定是相同的，
         * 故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
         * 由于字符串只包含小写字母，因此使用长度为26的数组记录每个字母出现的次数。
         * 需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同会有不同的实现方式。
         */
//        Map<String, List<String>> map = new HashMap<>();
//        for (String str : strs) {
//            int[] counts = new int[26];
//            int len = str.length();
//            for (int i = 0; i < len; i++) {
//                counts[str.charAt(i) - 'a']++;
//            }
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < 26; i++) {
//                if (counts[i] != 0) {
//                    sb.append((char) ('a' + i));
//                    sb.append(counts[i]);
//                }
//            }
//            String key = sb.toString();
//            List<String> list = map.getOrDefault(key, new ArrayList<String>());
//            list.add(str);
//            map.put(key, list);
//        }
//        return new ArrayList<List<String>>(map.values());

        //方法2的另一版本 5ms
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = new char[26];
            for (char c : s.toCharArray()) charArray[c - 'a']++;
            String key = String.valueOf(charArray);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
