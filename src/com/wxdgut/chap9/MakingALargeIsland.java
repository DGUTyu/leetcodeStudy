package com.wxdgut.chap9;

import javafx.util.Pair;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-07 20:55:00
 * <p>
 * 827. 最大人工岛  难度：困难
 * 链接：https://leetcode-cn.com/problems/making-a-large-island
 * <p>
 * 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
 * 返回执行此操作后，grid 中最大的岛屿面积是多少？
 * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
 * <p>
 * 示例:
 * 输入: grid = [[1, 0], [0, 1]]  输出: 3
 * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
 * 输入: grid = [[1, 1], [1, 0]]  输出: 4
 * 解释: 将一格0变成1，岛屿的面积扩大为 4。
 * 输入: grid = [[1, 1], [1, 1]]  输出: 4
 * 解释: 没有0可以让我们变成1，面积依然为 4。
 * <p>
 * 提示：
 * n == m
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] 为 0 或 1
 */

public class MakingALargeIsland {
//    int[] arr = new int[1000000];
//
//    public int largestIsland(int[][] grid) {
//        //方法1 159ms
//        int res = 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        boolean[][] visited = new boolean[m][n];
//        int color = 0;
//        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1 && !visited[i][j]) {
//                    color++;
//                    dfs(grid, i, j, visited, color);
//                }
//            }
//        }
//        for (int i = 1; i < 100; i++) {
//            res = Math.max(arr[i], res);
//        }
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < m; j++) {
//                if (grid[i][j] == 0) {
//                    HashSet<Integer> set = new HashSet<>();
//                    for (int[] move : moves) {
//                        int r = i + move[0];
//                        int c = j + move[1];
//                        if (r >= 0 && c >= 0 && r < m && c < n) {
//                            set.add(grid[r][c]);
//                        }
//                    }
//                    int area = 1;
//                    for (int setItem : set) area += arr[setItem];
//                    res = Math.max(area, res);
//                }
//            }
//        }
//        return res;
//    }
//
//    void dfs(int[][] grid, int i, int j, boolean[][] visited, int color) {
//        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
//                || visited[i][j] == true || grid[i][j] == 0)
//            return;
//        grid[i][j] = color;
//        arr[color]++;
//        visited[i][j] = true;
//        dfs(grid, i + 1, j, visited, color);
//        dfs(grid, i - 1, j, visited, color);
//        dfs(grid, i, j + 1, visited, color);
//        dfs(grid, i, j - 1, visited, color);
//    }

    public int largestIsland(int[][] grid) {
        /**方法2
         * 整体思路：首先将找出所有的岛屿并将同一岛屿的格子标成相同的数字，
         * 然后遍历非岛屿的点，判断该点与周边岛屿的连通情况
         * 如int[][]{{1,1,0},{0,0,0},{1,1,1}}转换后为{{2,2,0},{0,0,0},{3,3,3}}
         * 此时connectedList[0]=2意思是有2个岛屿相连，connectedList[1]=3
         */
        //List存储各岛屿的大小，下标为岛屿标号减2（这样可以原地更新数组，减少空间复杂度）
        List<Integer> connectedList = new ArrayList<>();
        int n = grid.length;
        int number = 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int[] sizeArray = new int[]{0};//记录该岛屿的大小
                    //岛屿标号，从2开始，表示该点已经遍历过，这样可以原地更新数组
                    number++;
                    dfs(grid, i, j, number, sizeArray);
                    connectedList.add(sizeArray[0]);
                }
            }
        }
        //考虑到不存在海的点的情况
        if (!connectedList.isEmpty()) res = connectedList.get(0);
        else return 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int area = 1;//记录当[i,j]变为陆地时，形成的岛屿大小
                    int up = -1;//避免[i,j]向上下左右遍历时，重复遍历相同的岛屿
                    int down = -1;
                    int left = -1;
                    if (i > 0 && grid[i - 1][j] > 0) {
                        area += connectedList.get(grid[i - 1][j] - 2);
                        up = grid[i - 1][j];
                    }
                    if (i < n - 1 && grid[i + 1][j] > 0 && grid[i + 1][j] != up) {
                        area += connectedList.get(grid[i + 1][j] - 2);
                        down = grid[i + 1][j];
                    }
                    if (j > 0 && grid[i][j - 1] > 0 && grid[i][j - 1] != up
                            && grid[i][j - 1] != down) {
                        area += connectedList.get(grid[i][j - 1] - 2);
                        left = grid[i][j - 1];
                    }
                    if (j < n - 1 && grid[i][j + 1] > 0 && grid[i][j + 1] != up
                            && grid[i][j + 1] != down && grid[i][j + 1] != left)
                        area += connectedList.get(grid[i][j + 1] - 2);
                    res = Math.max(area, res);
                }
            }
        }
        return res;
    }

    //dfs寻找所有连通的点，并标记为相同的数字number（number从2开始）
    public void dfs(int[][] grid, int i, int j, int number, int[] sizeArray) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid.length
                || grid[i][j] != 1)
            return;
        grid[i][j] = number;
        sizeArray[0]++;
        dfs(grid, i - 1, j, number, sizeArray);
        dfs(grid, i + 1, j, number, sizeArray);
        dfs(grid, i, j - 1, number, sizeArray);
        dfs(grid, i, j + 1, number, sizeArray);
    }
}
