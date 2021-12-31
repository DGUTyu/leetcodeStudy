package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-31 09:10:57
 * <p>
 * 126. 单词接龙 II 难度：困难
 * 链接：https://leetcode-cn.com/problems/word-ladder-ii
 * <p>
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，
 * 一个表示此过程的 转换序列 是形式上
 * 像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 * 1.每对相邻的单词之间仅有单个字母不同。
 * 2.转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。
 * 注意，beginWord 不必是字典 wordList 中的单词。
 * 3.sk == endWord
 * <p>
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。
 * 请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，
 * 如果不存在这样的转换序列，返回一个空列表。
 * 每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 * <p>
 * 示例：
 * 输入：beginWord = "hit", endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * 输入：beginWord = "hit", endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 * <p>
 * 提示：
 * ① 1 <= beginWord.length <= 7
 * ② endWord.length == beginWord.length
 * ③ 1 <= wordList.length <= 5000
 * ④ wordList[i].length == beginWord.length
 * ⑤ beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * ⑥ beginWord != endWord
 * ⑦ wordList 中的所有单词 互不相同
 */
public class WordLadderII {
    /**
     * 方法1：广度优先遍历、深度优先遍历（回溯） 12ms
     * <p>
     * 思路分析:
     * 可以构建出这些单词之间的关系是无权图，每个顶点之间的权重视为 1。
     * 题目要我们找 最短转换序列，提示我们需要使用 广度优先遍历。
     * 广度优先遍历就是用于找无权图的最短路径。
     * 与绝大多数使用广度优先遍历，
     * 只要求我们返回最短路径是多少的问题（本题的前置问题 127. 单词接龙 ）相比，
     * 本题要求返回 所有 从 beginWord 到 endWord 的最短转换序列，
     * 提示我们需要使用搜索算法（回溯算法、深度优先遍历）完成。
     * 在广度优先遍历的时候，我们需要记录：从当前的单词 currWord 只变化了一个字符以后，
     * 且又在单词字典中的单词 nextWord 之间的单向关系（虽然实际上无向图，
     * 但是广度优先遍历是有方向的，我们解决这个问题可以只看成有向图），记为 from，
     * 它是一个映射关系：键是单词，
     * 值是广度优先遍历的时候从哪些单词可以遍历到「键」所表示的单词，使用哈希表来保存。
     * <p>
     * 难点和注意事项:
     * 由于位于广度优先遍历同一层的单词如果它们之间有边连接，不可以被记录下来。
     * 因此需要一个哈希表记录遍历到的单词在第几层。
     */
//    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//        List<List<String>> res = new ArrayList<>();
//        Set<String> dictSet = new HashSet<>(wordList);
//        if (!dictSet.contains(endWord)) return res;
//        // 因为从 beginWord 开始扩展，因此 dictSet 里一定不可以有 beginWord
//        dictSet.remove(beginWord);
//        // 第 1 步：广度优先遍历构建图
//        // 为了避免记录不需要的边，我们需要记录扩展出的单词是在第几次扩展的时候得到的，
//        // key：单词，value：在广度优先遍历的第几层
//        // stepsMap 记录了已经访问过的 word 集合，同时记录了在第几层访问到
//        Map<String, Integer> stepsMap = new HashMap<>();
//        stepsMap.put(beginWord, 0);
//        // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，
//        // 这些单词可以变换到 key ，它们是一对多关系，dfs 的时候会用到
//        Map<String, Set<String>> fromMap = new HashMap<>();
//        boolean found = bfs(beginWord, endWord, dictSet, stepsMap, fromMap);
//        // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，
//        // 所以每次尝试操作 deque 列表的头部
//        if (found) {
//            Deque<String> deque = new ArrayDeque<>();
//            deque.add(endWord);
//            dfs(fromMap, deque, beginWord, endWord, res);
//        }
//        return res;
//    }
//
//    private boolean bfs(String beginWord, String endWord, Set<String> dictSet, Map<String, Integer> stepsMap, Map<String, Set<String>> fromMap) {
//        int wordLen = beginWord.length();
//        int step = 0;
//        boolean found = false;
//        Queue<String> queue = new LinkedList<>();
//        queue.offer(beginWord);
//        while (!queue.isEmpty()) {
//            step++;
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                String currWord = queue.poll();
//                char[] charArray = currWord.toCharArray();
//                for (int j = 0; j < wordLen; j++) {
//                    char old = charArray[j];
//                    for (char c = 'a'; c <= 'z'; c++) {
//                        charArray[j] = c;
//                        String nextWord = String.valueOf(charArray);
//                        // 注意：这几行代码的逻辑先后顺序
//                        if (stepsMap.containsKey(nextWord) && stepsMap.get(nextWord) == step) {
//                            fromMap.get(nextWord).add(currWord);
//                        }
//                        if (!dictSet.contains(nextWord)) continue;
//                        dictSet.remove(nextWord);
//                        // dictSet 和 stepsMap 承担了已经访问的功能
//                        queue.offer(nextWord);
//                        // 维护 fromMap、stepsMap、found 的定义,注意使用Java HashMap putIfAbsent() 方法
//                        // putIfAbsent() 方法会先判断指定的键（key）是否存在，不存在则将键/值对插入到 HashMap 中。
//                        fromMap.putIfAbsent(nextWord, new HashSet<>());
//                        fromMap.get(nextWord).add(currWord);
//                        stepsMap.put(nextWord, step);
//                        if (nextWord.equals(endWord)) {
//                            // 注意：由于有多条路径到达 endWord 找到以后不能立即退出，
//                            // 只需要设置 found = true 即可
//                            found = true;
//                        }
//                    }
//                    charArray[j] = old;
//                }
//            }
//            if (found) break;
//        }
//        return found;
//    }
//
//    private void dfs(Map<String, Set<String>> fromMap, Deque<String> deque, String beginWord, String curStr, List<List<String>> res) {
//        if (curStr.equals(beginWord)) {
//            res.add(new ArrayList<>(deque));
//            return;
//        }
//        for (String precursor : fromMap.get(curStr)) {
//            deque.addFirst(precursor);
//            dfs(fromMap, deque, beginWord, precursor, res);
//            deque.removeFirst();
//        }
//    }

    /**
     * 方法2：双向BFS 穷举DFS 3ms
     * 哈希表 去重。快速检索
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dictSet.contains(endWord)) return res;
        Map<String, List<String>> fromMap = GetChildrenBFS(beginWord, endWord, dictSet); //BFS
        List<String> pathList = new ArrayList<>(); //记录变化路径
        pathList.add(beginWord); //添加第一个单词
        findLaddersDFS(beginWord, endWord, fromMap, res, pathList); //DFS
        return res;
    }

    //广度遍历，跟第一个单词比较接近的
    public Map<String, List<String>> GetChildrenBFS(String beginWord, String endWord, Set<String> dictSet) {
        Map<String, List<String>> fromMap = new HashMap<>(); //记录了单词是从哪些单词扩展而来
        Set<String> startSet = new HashSet<>(); //开始集合
        Set<String> endSet = new HashSet<>(); //结束集合
        startSet.add(beginWord);
        endSet.add(endWord);
        Set<String> visitedSet = new HashSet<>(); //访问过的集合
        boolean found = false; //标记找到
        boolean isBackWard = false; //标记反向
        while (!startSet.isEmpty() && !found) { //startSet不为空且没有找到
            if (startSet.size() > endSet.size()) { //反向搜索
                Set<String> temp = startSet;
                startSet = endSet;
                endSet = temp; //交换start endSet
                isBackWard = !isBackWard; //取反标记
            }
            Set<String> newSet = new HashSet<>(); //新开辟集合，保存广度遍历
            for (String curStr : startSet) { //循环start每个单词
                visitedSet.add(curStr); //标记访问过
                for (String nextStr : getNext(curStr, dictSet)) { //找到下一个字符
                    //判断是否访问过或者包含，若重复，不再继续
                    if (visitedSet.contains(nextStr) || startSet.contains(nextStr)) continue;
                    //结束的是否包含，若包含，则标记找到
                    if (endSet.contains(nextStr)) found = true;
                    newSet.add(nextStr);//保存字符串
                    String Parent = isBackWard ? nextStr : curStr;
                    String Child = isBackWard ? curStr : nextStr; //确定顺序  hit  hot
                    fromMap.putIfAbsent(Parent, new ArrayList<>()); //保存
                    fromMap.get(Parent).add(Child); //保存映射
                }
            }
            startSet = newSet; //start保存
        }
        return fromMap; //返回结果
    }

    //找到下一个序列
    public List<String> getNext(String curStr, Set<String> dictSet) {
        List<String> list = new ArrayList<>();
        char[] curArray = curStr.toCharArray();
        for (int i = 0; i < curArray.length; i++) {
            char old = curArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) continue;
                curArray[i] = c;
                String newStr = new String(curArray);
                if (dictSet.contains(newStr)) list.add(newStr);
            }
            curArray[i] = old;
        }
        return list;
    }

    //深度遍历
    public void findLaddersDFS(String beginWord, String endWord, Map<String, List<String>> fromMap,
                               List<List<String>> res, List<String> pathList) {
        if (beginWord.equals(endWord)) { //begin==end循环到最后
            res.add(new ArrayList<>(pathList)); //保存路径
        }
        if (!fromMap.containsKey(beginWord)) { //包含，路径无效
            return;
        }
        for (String word : fromMap.get(beginWord)) { //循环每个单词
            pathList.add(word);//添加单词
            findLaddersDFS(word, endWord, fromMap, res, pathList); //深度遍历
            pathList.remove(pathList.size() - 1); //删除最后一个
        }
    }

    /**
     * 方法3：BFS + DFS 7ms
     */
//    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
//        List<String> pathList = new ArrayList<>();
//        List<List<String>> res = new ArrayList<>();
//        HashMap<String, List<String>> graph = new HashMap<>();
//        HashSet<String> dictSet = new HashSet<>(wordList);
//        buildGraph(beginWord, endWord, graph, dictSet);
//        dfs(beginWord, endWord, graph, pathList, res);
//        return res;
//    }
//
//    private void buildGraph(String beginWord, String endWord, HashMap<String, List<String>> graph, HashSet<String> dictSet) {
//        HashSet<String> visitedSet = new HashSet<>();
//        HashSet<String> toVisitSet = new HashSet<>();
//        Queue<String> queue = new LinkedList<>();
//        queue.offer(beginWord);
//        toVisitSet.add(beginWord);
//        boolean foundEnd = false;
//        while (!queue.isEmpty()) {
//            visitedSet.addAll(toVisitSet);
//            toVisitSet.clear();
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                String currStr = queue.poll();
//                List<String> childrenList = getNextLevel(currStr, dictSet);
//                for (String child : childrenList) {
//                    if (child.equals(endWord)) foundEnd = true;
//                    if (!visitedSet.contains(child)) {
//                        if (!graph.containsKey(currStr)) {
//                            graph.put(currStr, new ArrayList<>());
//                        }
//                        graph.get(currStr).add(child);
//                    }
//                    if (!visitedSet.contains(child) && !toVisitSet.contains(child)) {
//                        queue.offer(child);
//                        toVisitSet.add(child);
//                    }
//                }
//            }
//            if (foundEnd) break;
//        }
//    }
//
//    private List<String> getNextLevel(String currStr, HashSet<String> dict) {
//        List<String> list = new ArrayList<>();
//        char[] currArray = currStr.toCharArray();
//        for (int i = 0; i < currArray.length; i++) {
//            for (char c = 'a'; c <= 'z'; c++) {
//                if (currArray[i] == c) continue;
//                char old = currArray[i];
//                currArray[i] = c;
//                String newStr = String.valueOf(currArray);
//                if (dict.contains(newStr)) list.add(newStr);
//                currArray[i] = old;
//            }
//        }
//        return list;
//    }
//
//    private void dfs(String curWord, String endWord, HashMap<String, List<String>> graph, List<String> pathList, List<List<String>> res) {
//        pathList.add(curWord);
//        if (curWord.equals(endWord)) res.add(new ArrayList<>(pathList));
//        else if (graph.containsKey(curWord)) {
//            for (String word : graph.get(curWord)) {
//                dfs(word, endWord, graph, pathList, res);
//            }
//        }
//        pathList.remove(pathList.size() - 1);
//    }
}
