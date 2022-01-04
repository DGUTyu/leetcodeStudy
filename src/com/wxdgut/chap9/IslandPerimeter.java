package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-04 08:55:40
 * <p>
 * 463. 岛屿的周长  难度：简单
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * <p>
 * 给定一个 row x col 的二维网格地图 grid ，
 * 其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。
 * 整个网格被水完全包围，但其中恰好有一个岛屿
 * （或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
 * 格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。
 * 计算这个岛屿的周长。
 * <p>
 * 示例：
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]  输出：16
 * 输入：grid = [[1]]  输出：4
 * 输入：grid = [[1,0]]  输出：4
 * <p>
 * 提示：
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 */

public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        // 方法1：DFS 7ms
        // 岛屿的周长就是岛屿方格和非岛屿方格相邻的边的数量。
        // 注意，这里的非岛屿方格，既包括水域方格，也包括网格的边界。
        // 即周长=与网格边界相邻的周长+与水域方格相邻的周长
        if (grid == null) return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int i, int j) {
        // 从一个岛屿方格走向网格边界周长加1；从一个岛屿方格走向水域方格，周长加1
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
            return 1;
        if (grid[i][j] == -1) return 0;
        int count = 0;
        grid[i][j] = -1;
        count += dfs(grid, i + 1, j);
        count += dfs(grid, i - 1, j);
        count += dfs(grid, i, j + 1);
        count += dfs(grid, i, j - 1);
        return count;
    }

//    public int islandPerimeter(int[][] grid) {
//        //方法2：BFS  时间复杂度：O(M*N) 13ms
//        if (grid == null || grid.length == 0) return 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 1) {
//                    return bfs(grid, i, j);
//                }
//            }
//        }
//        return 0;
//    }
//
//    private int bfs(int[][] grid, int i, int j) {
//        int n = 0;
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[]{i, j});
//        grid[i][j] = -1;
//        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        while (!queue.isEmpty()) {
//            int[] currIndex = queue.remove();
//            i = currIndex[0];
//            j = currIndex[1];
//            for (int[] move : moves) {
//                int r = i + move[0];
//                int c = j + move[1];
//                //if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
//                if (isArea(grid, r, c) && grid[r][c] == 1) {
//                    grid[r][c] = -1; //注意是 && r c
//                    queue.add(new int[]{r, c});
//                }
//                //if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) {
//                if (!isArea(grid, r, c) || grid[r][c] == 0) {
//                    n++; //注意是 || r c
//                }
//            }
//        }
//        return n;
//    }
//
//    public boolean isArea(int[][] grid, int r, int c) {
//        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
//    }

//    public int islandPerimeter(int[][] grid) {
//        /**
//         * 方法3：公式法 4ms
//         * 对于一个陆地格子的每条边，
//         * 它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
//         * 规律：对于单独的格子而言，周长是4，但是当两个格子相邻时，会各自损失1的周长。
//         * 所以问题转化为 找出 岛屿格子 之间的 相邻边。
//         */
//        // 举例推导出公式 res = 4 * 岛屿格子数量 - 2 * 岛屿格子之间的相邻边
//        int n = grid.length;
//        int m = grid[0].length;
//        int islands = 0; // 岛屿格子数量
//        int neighbours = 0; // 岛屿格子之间的相邻边
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (grid[i][j] == 1) {
//                    islands++;
//                    if (i < n - 1 && grid[i + 1][j] == 1) neighbours++; // 判断下面是不是 陆地格子
//                    if (j < m - 1 && grid[i][j + 1] == 1) neighbours++; // 判断右边是不是 陆地格子
//                }
//            }
//        }
//        return 4 * islands - 2 * neighbours;
//    }

//    public int islandPerimeter(int[][] grid) {
//        //方法3：改版 4ms
//        int result = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 1) {
//                    result += 4;
//                    if (i > 0 && grid[i - 1][j] == 1) result -= 2;
//                    if (j > 0 && grid[i][j - 1] == 1) result -= 2;
//                }
//            }
//        }
//        return result;
//    }

//    public int islandPerimeter(int[][] grid) {
//        //方法4：迭代 5ms
//        int res = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                if (grid[i][j] == 1) {   //左 上 右 下
//                    if (j == 0 || grid[i][j - 1] == 0) res++; //左
//                    if (i == 0 || grid[i - 1][j] == 0) res++; //上
//                    if (j == grid[i].length - 1 || grid[i][j + 1] == 0) res++; //右
//                    if (i == grid.length - 1 || grid[i + 1][j] == 0) res++; //下
//                }
//            }
//        }
//        return res;
//    }
}
