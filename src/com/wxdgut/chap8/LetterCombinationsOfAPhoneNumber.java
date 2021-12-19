package com.wxdgut.chap8;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-19 20:16:12
 * <p>
 * 17. 电话号码的字母组合 难度：中等
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 答案可以按 任意顺序 返回。给出数字到字母的映射如下（与电话按键相同）。
 * 注意 1 不对应任何字母。
 * <p>
 * 手机按键对应字母关系为：
 * 2:abc 3:def 4:ghi 5:jkl 6:mno 7:pqrs 8:tuv 9:wxyz
 * <p>
 * 示例：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */

public class LetterCombinationsOfAPhoneNumber {

//    public List<String> letterCombinations(String digits) {
//        //方法1：广度优先 队列思想 6ms
//        LinkedList<String> queue = new LinkedList<>();
//        if (digits.length() == 0) return queue;
//        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        queue.add("");
//        for (int i = 0; i < digits.length(); i++) {
//            int num = Character.getNumericValue(digits.charAt(i));
//            while (queue.peek().length() == i) {
//                String head = queue.remove();
//                for (char c : mapping[num].toCharArray())
//                    queue.add(head + c);
//            }
//        }
//        return queue;
//    }

//    public List<String> letterCombinations(String digits) {
//        //方法1：广度优先 队列思想 改版 5ms
//        LinkedList<String> queue = new LinkedList<>();
//        if (digits.length() == 0) return queue;
//        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        queue.add("");
//        while (queue.peek().length() != digits.length()) {
//            String head = queue.remove();
//            String charStr = mapping[digits.charAt(head.length()) - '0'];
//            for (char c : charStr.toCharArray()) {
//                queue.add(head + c);//queue.addLast(head + c);
//            }
//        }
//        return queue;
//    }

//    private String[] KEYS = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//
//    public List<String> letterCombinations(String digits) {
//        //方法2：递归 5ms
//        List<String> list = new LinkedList<>();
//        //以下这句不可少，用于处理示例：输入 ""
//        if (digits == null || digits.length() == 0) return list;
//        combination(digits, list, 0, "");
//        return list;
//    }
//
//    private void combination(String digits, List<String> list, int offset, String prefix) {
//        if (offset >= digits.length()) {
//            list.add(prefix);
//            return;
//        }
//        String letters = KEYS[(digits.charAt(offset) - '0')];
//        for (char c : letters.toCharArray()) {
//            combination(digits, list, offset + 1, prefix + c);
//        }
//    }

//    public List<String> letterCombinations(String digits) {
//        //方法2：递归 数组版本2 0ms
//        if (digits.length() == 0) return new ArrayList<>();
//        String[] dict = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//        List<String> list = new ArrayList<>();
//        backtrack(digits.toCharArray(), dict, list, "");
//        return list;
//    }
//
//    public void backtrack(char[] digitsArr, String[] dict, List<String> list, String str) {
//        if (str.length() == digitsArr.length) {
//            list.add(str);
//            return;
//        }
//        int i = str.length();
//        int num = digitsArr[i] - '0';
//        for (char c : dict[num].toCharArray()) {
//            backtrack(digitsArr, dict, list, str + Character.toString(c));
//        }
//    }

    private String[] KEYS = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        //方法3：回溯 数组 0ms
        if (digits.length() == 0) return new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        dfs(digits, KEYS, list, 0, sb);
        return list;
    }

    private void dfs(String digits, String[] keypad, List<String> list, int index, StringBuilder sb) {
        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }
        String options = keypad[digits.charAt(index) - '0'];
        for (char option : options.toCharArray()) {
            sb.append(option);
            dfs(digits, keypad, list, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

//    private Map<Character, String> KEYS = new HashMap<Character, String>() {{
//        put('2', "abc");
//        put('3', "def");
//        put('4', "ghi");
//        put('5', "jkl");
//        put('6', "mno");
//        put('7', "pqrs");
//        put('8', "tuv");
//        put('9', "wxyz");
//    }};
//
//    public List<String> letterCombinations(String digits) {
//        //方法3：回溯 Map 3ms
//        if (digits.length() == 0) return new ArrayList<String>();
//        StringBuilder sb = new StringBuilder();
//        List<String> list = new ArrayList<>();
//        dfs(digits, KEYS, list, 0, sb);
//        return list;
//    }
//
//    private void dfs(String digits, Map<Character, String> keypad, List<String> list, int index, StringBuilder sb) {
//        if (index == digits.length()) {
//            list.add(sb.toString());
//            return;
//        }
//        char num = digits.charAt(index);
//        String charStr = keypad.getOrDefault(num, num + "");
//        for (char c : charStr.toCharArray()) {
//            sb.append(c);
//            dfs(digits, keypad, list, index + 1, sb);
//            sb.deleteCharAt(sb.length() - 1);
//        }
//    }
}
