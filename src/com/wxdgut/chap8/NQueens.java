package com.wxdgut.chap8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-20 22:00:13
 * <p>
 * 51. N 皇后 难度：困难
 * 链接：https://leetcode-cn.com/problems/n-queens
 * <p>
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，
 * 并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，
 * 该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 皇后的走法是：可以横直斜走，格数不限。
 * 因此要求皇后彼此之间不能相互攻击，
 * 等价于要求任何两个皇后都不能在同一行、同一列以及同一条斜线上。
 * <p>
 * 示例：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 提示：1 <= n <= 9
 * <p>
 * 在一个n阶方阵(或是n阶行列式)中，从左上角到右下角这一斜线方向上的n 个元素所在的对角线，
 * 叫做n 阶方阵(或行列式)的主对角线。
 * 主对角线上元素的特点：（横坐标-纵坐标）的值固定
 * 副对角线上元素的特点：横纵坐标的和固定
 */

public class NQueens {

    private List<List<String>> res = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        //方法1：回溯 时间复杂度O(n!) 1ms
        char[][] arr = new char[n][n]; //棋盘
        //记录某一列是否放置了皇后
        boolean col[] = new boolean[n];
        //记录主对角线（副对角线）上的单元格是否放置了皇后
        //n=4时，主对角线（副对角线）有7根
        boolean main[] = new boolean[2 * n - 1];
        boolean sub[] = new boolean[2 * n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = '.'; //初始化棋盘
            }
        }
        dfs(n, arr, 0, col, main, sub);
        return res;
    }

    private void dfs(int n, char[][] arr, int row, boolean col[], boolean main[], boolean sub[]) {
        if (n == row) {
            for (int i = 0; i < n; i++) {
                list.add(new String(arr[i]));
            }
            res.add(new ArrayList<>(list));
            list.clear(); //不可少
        }
        //针对下标为 row 的每一列，尝试是否可以放置。i为横坐标，row为纵坐标
        for (int i = 0; i < n; i++) {
            if (col[i] || sub[i + row] || main[i - row + n - 1]) {
                continue;
            }
            arr[row][i] = 'Q';
            //（横坐标-纵坐标）的值固定，+(n - 1)是为了补全数组索引，使其可以正常使用
            col[i] = sub[i + row] = main[i - row + n - 1] = true;
            dfs(n, arr, row + 1, col, main, sub);
            col[i] = sub[i + row] = main[i - row + n - 1] = false;
            arr[row][i] = '.';
        }
    }

//    private List<List<String>> res = new ArrayList<>();
//    private List<String> list = new ArrayList<>();
//
//    public List<List<String>> solveNQueens(int n) {
//        //方法2：回溯 基于位运算 时间复杂度O(n!) 2ms
//        if (n < 1) return res;
//        int col = 0, aDiag = 0, diag = 0;
//        dfs(n, 0, col, aDiag, diag);
//        return res;
//    }
//
//    private void dfs(int n, int row, int col, int main, int sub) {
//        //recursion terminator
//        if (row >= n) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        //get available spots for the current row
//        int bits = (~(col | main | sub)) & ((1 << n) - 1);
//        while (bits > 0) {
//            int p = bits & -bits;
//            int pos = Integer.toBinaryString(p).length();
//            char[] boardRow = new char[n];
//            Arrays.fill(boardRow, '.');
//            boardRow[n - pos] = 'Q';
//            list.add(String.valueOf(boardRow));
//            dfs(n, row + 1, col | p, (main | p) << 1, (sub | p) >> 1);
//            list.remove(list.size() - 1);
//            bits &= bits - 1;
//        }
//    }

}
