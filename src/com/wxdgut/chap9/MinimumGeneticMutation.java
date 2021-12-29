package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-28 10:34:14
 * <p>
 * 433. 最小基因变化  难度：中等
 * 链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
 * <p>
 * 一条基因序列由一个带有8个字符的字符串表示，
 * 其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，
 * 请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。
 * 如果无法实现目标变化，请返回 -1。
 * <p>
 * 注意：
 * 1.起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 2.如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。
 * 3.假定起始基因序列与目标基因序列是不一样的。
 * <p>
 * 示例：
 * start:"AACCGGTT"  end:"AACCGGTA"  bank:["AACCGGTA"]
 * 返回值: 1
 * start:"AACCGGTT"  end:"AAACGGTA"  bank:["AACCGGTA","AACCGCTA","AAACGGTA"]
 * 返回值: 2
 * start:"AAAAACCC"  end:"AACCCCCC"  bank:["AAAACCCC","AAACCCCC","AACCCCCC"]
 * 返回值: 3
 */

public class MinimumGeneticMutation {

//    public int minMutation(String start, String end, String[] bank) {
//        //方法1 BFS 使用queue
//        Queue<String> queue = new ArrayDeque<>();
//        Set<String> visitedSet = new HashSet<>();
//        if (!contains(bank, end) && !start.equals(end)) return -1;
//        queue.add(start);
//        visitedSet.add(start);
//        int res = 0;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                String curr = queue.remove();
//                if (curr.equals(end)) return res;
//                for (String str : bank) {
//                    if (canMutate(str, curr) && !visitedSet.contains(str)) {
//                        queue.add(str);
//                        visitedSet.add(str);
//                    }
//                }
//            }
//            res++;
//        }
//        return -1;
//    }

    public int minMutation(String start, String end, String[] bank) {
        //方法1 BFS 使用deque
        Deque<String> deque = new ArrayDeque<>();
        Set<String> visitedSet = new HashSet<>();
        if (!contains(bank, end) && !start.equals(end)) return -1;
        deque.add(start);
        visitedSet.add(start);
        int res = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String curr = deque.removeFirst();
                if (curr.equals(end)) return res;
                for (String str : bank) {
                    if (canMutate(str, curr) && !visitedSet.contains(str)) {
                        deque.add(str);
                        visitedSet.add(str);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    private boolean canMutate(String str1, String str2) {
        int counter = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))
                counter++;
        }
        return counter == 1;
    }

    private boolean contains(String[] band, String toFind) {
        for (String str : band) {
            if (str.contains(toFind))
                return true;
        }
        return false;
    }

//    public int minMutation(String start, String end, String[] bank) {
//        //方法2 BFS 使用queue
//        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
//        char[] charSet = new char[]{'A', 'C', 'G', 'T'};
//        if (!bankSet.contains(end)) return -1;
//        Queue<String> queue = new LinkedList<>();
//        Set<String> visitedSet = new HashSet<>();
//        queue.offer(start);
//        visitedSet.add(start);
//        int level = 0;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                String curr = queue.poll();
//                if (curr.equals(end)) return level;
//                char[] currArray = curr.toCharArray();
//                for (int i = 0; i < currArray.length; i++) {
//                    char old = currArray[i];
//                    for (char c : charSet) {
//                        currArray[i] = c;
//                        String newSeq = new String(currArray);
//                        if (!visitedSet.contains(newSeq) && bankSet.contains(newSeq)) {
//                            visitedSet.add(newSeq);
//                            queue.add(newSeq);
//                        }
//                    }
//                    currArray[i] = old;
//                }
//            }
//            level++;
//        }
//        return -1;
//    }

//    int ans = Integer.MAX_VALUE;
//
//    public int minMutation(String start, String end, String[] bank) {
//        //方法3：DFS
//        dfs(start, bank, end, 0);
//        return ans == Integer.MAX_VALUE ? -1 : ans;
//    }
//
//    void dfs(String start, String[] bank, String end, int steps) {
//        if (steps >= ans) return;
//        //for (String b : bank) //下面不可用迭代器遍历
//        for (int i = 0; i < bank.length; i++) {
//            if (bank[i] != "" && isValidPath(start, bank[i])) {
//                String val = bank[i];
//                bank[i] = "";
//                dfs(val, bank, end, steps + 1);
//                bank[i] = val;
//                if (bank[i].equals(end)) {
//                    ans = Math.min(ans, steps + 1);
//                    return;
//                }
//            }
//        }
//    }
//
//    boolean isValidPath(String s, String d) {
//        if (s.equals(d)) return false;
//        boolean once = false;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != d.charAt(i)) {
//                if (once) return false;
//                once = true;
//            }
//        }
//        return true;
//    }
}
