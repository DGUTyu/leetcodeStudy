package com.wxdgut.chap9;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-09 21:40:50
 * <p>
 * 529. 扫雷游戏  难度：中等
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * <p>
 * 友情链接：在线扫雷游戏 http://wxdgut.com/code/minesweeper
 * <p>
 * 让我们一起来玩扫雷游戏！
 * <p>
 * 给你一个大小为 m x n 二维字符矩阵 board ，表示扫雷游戏的盘面，其中：
 * 1.'M' 代表一个 未挖出的 地雷，
 * 2.'E' 代表一个 未挖出的 空方块，
 * 3.'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的 已挖出的 空白方块，
 * 4.数字（'1' 到 '8'）表示有多少地雷与这块 已挖出的 方块相邻，
 * 5.'X' 则表示一个 已挖出的 地雷。
 * <p>
 * 给你一个整数数组 click ，其中 click = [clickr, clickc]
 * 表示在所有 未挖出的 方块（'M' 或者 'E'）中的下一个点击位置
 * （clickr 是行下标，clickc 是列下标）。
 * <p>
 * 根据以下规则，返回相应位置被点击后对应的盘面：
 * 1.如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X' 。
 * 2.如果一个 没有相邻地雷 的空方块（'E'）被挖出，修改它为（'B'），
 * 并且所有和其相邻的 未挖出 方块都应该被递归地揭露。
 * 3.如果一个 至少与一个地雷相邻 的空方块（'E'）被挖出，
 * 修改它为数字（'1' 到 '8' ），表示相邻地雷的数量。
 * 4.如果在此次点击中，若无更多方块可被揭露，则返回盘面。
 * <p>
 * 示例：
 * 输入：board = [
 * ["E","E","E","E","E"],
 * ["E","E","M","E","E"],
 * ["E","E","E","E","E"],
 * ["E","E","E","E","E"]], click = [3,0]
 * 输出：
 * [["B","1","E","1","B"],
 * ["B","1","M","1","B"],
 * ["B","1","1","1","B"],
 * ["B","B","B","B","B"]]
 * 输入：board = [
 * ["B","1","E","1","B"],
 * ["B","1","M","1","B"],
 * ["B","1","1","1","B"],
 * ["B","B","B","B","B"]], click = [1,2]
 * 输出：
 * [["B","1","E","1","B"],
 * ["B","1","X","1","B"],
 * ["B","1","1","1","B"],
 * ["B","B","B","B","B"]]
 * <p>
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 50
 * board[i][j] 为 'M'、'E'、'B' 或数字 '1' 到 '8' 中的一个
 * click.length == 2
 * 0 <= clickr < m
 * 0 <= clickc < n
 * board[clickr][clickc] 为 'M' 或 'E'
 */

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        //方法1：DFS 0ms
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];
        int count = 0;
        if (board[row][col] == 'M') board[row][col] = 'X';
        else {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) continue;
                    int r = row + i, c = col + j;
                    if (r < 0 || r >= m || c < 0 || c >= n) continue;
                    if (board[r][c] == 'M' || board[r][c] == 'X') count++;
                }
            }
            if (count > 0) board[row][col] = (char) (count + '0');
            else {
                board[row][col] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = row + i, c = col + j;
                        if (r < 0 || r >= m || c < 0 || c >= n) continue;
                        if (board[r][c] == 'E') updateBoard(board, new int[]{r, c});
                    }
                }
            }
        }
        return board;
    }

//    public char[][] updateBoard(char[][] board, int[] click) {
//        //方法1：DFS 改版 0ms
//        int m = board.length, n = board[0].length;
//        int row = click[0], col = click[1];
//        if (board[row][col] == 'M') board[row][col] = 'X';
//        else {
//            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
//            dfs(board, row, col, m, n, dirs);
//        }
//        return board;
//    }
//
//    private void dfs(char[][] board, int row, int col, int m, int n, int[][] moves) {
//        if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] != 'E') return;
//        int mine = adjMine(board, row, col, m, n);
//        if (mine > 0) board[row][col] = (char) ('0' + mine);
//        else {
//            board[row][col] = 'B';
//            for (int[] move : moves) {
//                dfs(board, row + move[0], col + move[1], m, n, moves);
//            }
//        }
//    }
//
//    private int adjMine(char[][] board, int row, int col, int m, int n) {
//        int count = 0;
//        for (int i = row - 1; i <= row + 1; i++) {
//            for (int j = col - 1; j <= col + 1; j++) {
//                if (0 <= i && i < m && 0 <= j && j < n && board[i][j] == 'M')
//                    count++;
//            }
//        }
//        return count;
//    }
//

//    public char[][] updateBoard(char[][] board, int[] click) {
//        //方法2：BFS 3ms
//        int m = board.length, n = board[0].length;
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(click);
//        while (!queue.isEmpty()) {
//            int[] cur = queue.poll();
//            int row = cur[0], col = cur[1];
//            if (board[row][col] == 'M') board[row][col] = 'X';
//            else {
//                int count = 0;
//                for (int i = -1; i < 2; i++) {
//                    for (int j = -1; j < 2; j++) {
//                        if (i == 0 && j == 0) continue;
//                        int r = row + i, c = col + j;
//                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n) continue;
//                        if (board[r][c] == 'M' || board[r][c] == 'X') count++;
//                    }
//                }
//                if (count > 0) board[row][col] = (char) (count + '0');
//                else {
//                    board[row][col] = 'B';
//                    for (int i = -1; i < 2; i++) {
//                        for (int j = -1; j < 2; j++) {
//                            if (i == 0 && j == 0) continue;
//                            int r = row + i, c = col + j;
//                            if (r < 0 || r >= m || c < 0 || c >= n) continue;
//                            if (board[r][c] == 'E') {
//                                queue.add(new int[]{r, c});
//                                board[r][c] = 'B';
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return board;
//    }
}
