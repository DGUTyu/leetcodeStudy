package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-03 21:55:46
 * <p>
 * 200. 岛屿数量  难度：中等
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * 示例：
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 */

public class NumberOfIslands {
    //    public int numIslands(char[][] grid) {
//        //方法1：DFS  时间复杂度：O(M*N) 3ms
//        int count = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == '1') {
//                    dfs(grid, i, j);
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//
//    private void dfs(char[][] grid, int i, int j) {
//        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0')
//            return;
//        grid[i][j] = '0';
//        dfs(grid, i + 1, j);
//        dfs(grid, i - 1, j);
//        dfs(grid, i, j + 1);
//        dfs(grid, i, j - 1);
//    }

    public int numIslands(char[][] grid) {
        //方法1：DFS  时间复杂度：O(M*N) 2ms
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfsB(grid, i, j, m, n);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfsB(char[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfsB(grid, r - 1, c, m, n);
        dfsB(grid, r + 1, c, m, n);
        dfsB(grid, r, c - 1, m, n);
        dfsB(grid, r, c + 1, m, n);
    }

//    public int numIslands(char[][] grid) {
//        //方法2：BFS  时间复杂度：O(M*N) 7ms
//        int count = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == '1') {
//                    bfs(grid, i, j);
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//
//    private void bfs(char[][] grid, int i, int j) {
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[]{i, j});
//        while (!queue.isEmpty()) {
//            int[] currIndex = queue.remove();
//            i = currIndex[0];
//            j = currIndex[1];
//            if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] != '0') {
//                grid[i][j] = '0';
//                queue.add(new int[]{i + 1, j});
//                queue.add(new int[]{i - 1, j});
//                queue.add(new int[]{i, j + 1});
//                queue.add(new int[]{i, j - 1});
//            }
//        }
//    }
}
