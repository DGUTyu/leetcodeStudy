package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-30 17:36:24
 * <p>
 * 127. 单词接龙  难度：困难
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * <p>
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * 1.序列中第一个单词是 beginWord 。
 * 2.序列中最后一个单词是 endWord 。
 * 3.每次转换只能改变一个字母。
 * 4.转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，
 * 找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0。
 * <p>
 * 示例：
 * 输入：beginWord = "hit", endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 输入：beginWord = "hit", endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 提示：
 * ① 1 <= beginWord.length <= 10
 * ② endWord.length == beginWord.length
 * ③ 1 <= wordList.length <= 5000
 * ④ wordList[i].length == beginWord.length
 * ⑤ beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * ⑥ beginWord != endWord
 * ⑦ wordList 中的所有字符串 互不相同
 */

public class WordLadder {

//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        //666方法1：单向BFS 67ms
//        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
//        Set<String> wordSet = new HashSet<>(wordList);
//        if (!wordSet.contains(endWord)) return 0;
//        wordSet.remove(beginWord);
//        // 第 2 步：图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
//        Queue<String> queue = new LinkedList<>();
//        queue.offer(beginWord);
//        Set<String> visitedSet = new HashSet<>();
//        visitedSet.add(beginWord);
//        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
//        int step = 1;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                String currStr = queue.poll();
//                char[] charArray = currStr.toCharArray();
//                for (int j = 0; j < charArray.length; j++) {
//                    char old = charArray[j]; // 先保存，然后恢复
//                    for (char c = 'a'; c <= 'z'; c++) {
//                        if (old == c) continue;
//                        charArray[j] = c;
//                        String newStr = new String(charArray);
//                        if (wordSet.contains(newStr)) {
//                            if (newStr.equals(endWord)) return step + 1;
//                            if (!visitedSet.contains(newStr)) {
//                                queue.add(newStr);
//                                // 注意：添加到队列以后，必须马上标记为已经访问
//                                visitedSet.add(newStr);
//                            }
//                        }
//                    }
//                    charArray[j] = old; // 恢复
//                }
//            }
//            step++; //开始下一层
//        }
//        return 0;
//    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //方法2：双向BFS 13ms
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        Set<String> visitedSet = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisitedSet = new HashSet<>();
        beginVisitedSet.add(beginWord);
        Set<String> endVisitedSet = new HashSet<>();
        endVisitedSet.add(endWord);
        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVisitedSet.isEmpty() && !endVisitedSet.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisitedSet.size() > endVisitedSet.size()) {
                Set<String> temp = beginVisitedSet;
                beginVisitedSet = endVisitedSet;
                endVisitedSet = temp;
            }
            // 逻辑到这里，保证 beginVisited 是相对较小的集合，
            // nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisitedSet) {
                char[] charArray = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char old = charArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (old == c) continue;
                        charArray[i] = c;
                        String nextWord = String.valueOf(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisitedSet.contains(nextWord))
                                return step + 1;
                            if (!visitedSet.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                visitedSet.add(nextWord);
                            }
                        }
                    }
                    charArray[i] = old; // 恢复
                }
            }
            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS
            beginVisitedSet = nextLevelVisited;
            step++;
        }
        return 0;
    }

//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        //方法2：双向BFS改版 9ms
//        HashSet<String> wordSet = new HashSet<>(wordList);
//        if (!wordSet.contains(endWord)) return 0;
//        Queue<String> beginQueue = new LinkedList<>();// 从begin开始的队列
//        Queue<String> endQueue = new LinkedList<>();// 从end开始的队列
//        Map<String, Integer> beginMap = new HashMap<>();
//        Map<String, Integer> endMap = new HashMap<>();
//        beginQueue.add(beginWord);
//        beginMap.put(beginWord, 0);
//        endQueue.add(endWord);
//        endMap.put(endWord, 0);
//        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
//            int result = -1;
//            if (beginQueue.size() <= endQueue.size()) {
//                result = bfs(beginQueue, beginMap, endMap, wordSet);
//            } else {
//                result = bfs(endQueue, endMap, beginMap, wordSet);
//            }
//            if (result != -1)
//                return result + 1;
//        }
//        return 0;
//    }
//
//    private int bfs(Queue<String> queue, Map<String, Integer> map, Map<String, Integer> otherMap, HashSet<String> wordSet) {
//        String currStr = queue.poll();
//        char[] charArray = currStr.toCharArray();
//        for (int i = 0; i < charArray.length; i++) {
//            char old = charArray[i];
//            for (char c = 'a'; c <= 'z'; c++) {
//                if (old == c) continue;
//                charArray[i] = c;
//                String newStr = new String(charArray);
//                if (wordSet.contains(newStr)) {
//                    if (otherMap.containsKey(newStr))
//                        return map.get(currStr) + otherMap.get(newStr) + 1;
//                    if (!map.containsKey(newStr)) {
//                        queue.offer(newStr);
//                        map.put(newStr, map.get(currStr) + 1);
//                    }
//                }
//            }
//            charArray[i] = old;
//        }
//        return -1;
//    }

//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        //方法3：递归 11ms
//        if (beginWord.equals(endWord) || !wordList.contains(endWord))
//            return 0;
//        Set<String> beginSet = new HashSet<>();
//        beginSet.add(beginWord);
//        Set<String> endSet = new HashSet<>();
//        endSet.add(endWord);
//        Set<String> wordSet = new HashSet<>(wordList);
//        return bfs(beginSet, endSet, wordSet, 1);
//    }
//
//    private int bfs(Set<String> beginSet, Set<String> endSet, Set<String> wordSet, int level) {
//        if (beginSet.isEmpty() || endSet.isEmpty()) return 0;
//        wordSet.removeAll(beginSet);
//        Set<String> nextSet = new HashSet<>();
//        for (String beginWord : beginSet) {
//            char[] beginWordArr = beginWord.toCharArray();
//            for (int i = 0; i < beginWordArr.length; i++) {
//                char old = beginWordArr[i];
//                for (char c = 'a'; c <= 'z'; c++) {
//                    beginWordArr[i] = c;
//                    String newWord = new String(beginWordArr);
//                    if (!wordSet.contains(newWord)) continue;
//                    if (endSet.contains(newWord)) return level + 1;
//                    nextSet.add(newWord);
//                }
//                beginWordArr[i] = old;
//            }
//        }
//        return nextSet.size() > endSet.size() ?
//                bfs(endSet, nextSet, wordSet, level + 1) :
//                bfs(nextSet, endSet, wordSet, level + 1);
//    }
}
