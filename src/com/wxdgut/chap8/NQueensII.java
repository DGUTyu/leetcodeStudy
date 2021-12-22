package com.wxdgut.chap8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-22 11:20:25
 * <p>
 * 52. N皇后 II 难度：困难
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，
 * 并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 示例：输入：n = 4  输出：2
 */

public class NQueensII {

    private int count = 0;

    public int totalNQueens(int n) {
        //方法1：回溯 N皇后改造 0ms
        boolean col[] = new boolean[n];
        boolean main[] = new boolean[2 * n - 1];
        boolean sub[] = new boolean[2 * n - 1];
        dfs(n, 0, col, main, sub);
        return count;
    }

    private void dfs(int n, int row, boolean col[], boolean main[], boolean sub[]) {
        if (n == row) {
            count++;
            return; //此句也可以删除
        }
        for (int i = 0; i < n; i++) {
            if (col[i] || sub[i + row] || main[i - row + n - 1]) continue;
            col[i] = sub[i + row] = main[i - row + n - 1] = true;
            dfs(n, row + 1, col, main, sub);
            col[i] = sub[i + row] = main[i - row + n - 1] = false;
        }
    }

//    public int totalNQueens(int n) {
//        //方法2：回溯 基于位运算 0ms
//        return solve(n, 0, 0, 0, 0);
//    }
//
//    public int solve(int n, int row, int col, int main, int sub) {
//        if (row == n) {
//            return 1;
//        } else {
//            int count = 0;
//            int availablePos = ((1 << n) - 1) & (~(col | main | sub));
//            while (availablePos != 0) {
//                int position = availablePos & (-availablePos);
//                availablePos = availablePos & (availablePos - 1);
//                count += solve(n, row + 1, col | position, (main | position) << 1, (sub | position) >> 1);
//            }
//            return count;
//        }
//    }
}
