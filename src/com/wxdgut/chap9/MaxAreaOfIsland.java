package com.wxdgut.chap9;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-06 20:16:34
 * <p>
 * 695. 岛屿的最大面积 难度：中等
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * <p>
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
 * 这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * <p>
 * 示例：
 * 输入：grid = [[0,0,0,0,0,0,0,0]]  输出：0
 * 输入：grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 */

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        //方法1：DFS 时间复杂度：O(R×C) 2ms
        //为了确保每个土地访问不超过一次，每次经过一块土地时，将这块土地的值置为 0
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfsE(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfsA(int[][] grid, int i, int j) { //2ms
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        grid[i][j] = 0; //与if对应，不可写为grid[i][j] = 非零值;
        int area = 1;
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] move : moves) {
            int r = i + move[0];
            int c = j + move[1];
            area += dfsA(grid, r, c);
        }
        return area;
    }

    private int dfsB(int[][] grid, int i, int j) { //2ms
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        grid[i][j] = 0;
        int area = 1;
        int[] moves = new int[]{0, 1, 0, -1, 0};
        for (int k = 0; k < 4; k++)
            area += dfsB(grid, i + moves[k], j + moves[k + 1]);
        return area;
    }

    private int dfsC(int[][] grid, int i, int j) { //2ms
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        grid[i][j] = 0;
        int area = 1;
        area += dfsC(grid, i + 1, j);
        area += dfsC(grid, i - 1, j);
        area += dfsC(grid, i, j + 1);
        area += dfsC(grid, i, j - 1);
        return area;
    }

//    private int dfsD(int[][] grid, int i, int j) { //4ms
//        int m = grid.length;
//        int n = grid[0].length;
//        Deque<int[]> stack = new LinkedList<>();
//        stack.push(new int[]{i, j});
//        grid[i][j] = 0;
//        int area = 1;
//        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        while (!stack.isEmpty()) {
//            int[] cell = stack.pop();
//            for (int[] move : moves) {
//                int r = move[0] + cell[0];
//                int c = move[1] + cell[1];
//                if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
//                    grid[r][c] = 0;
//                    area++;
//                    stack.push(new int[]{r, c});
//                }
//            }
//        }
//        return area;
//    }
//
    private int dfsE(int[][] grid, int i, int j) { //2ms
        int m = grid.length;
        int n = grid[0].length;
        if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + dfsE(grid, i + 1, j) + dfsE(grid, i - 1, j)
                    + dfsE(grid, i, j + 1) + dfsE(grid, i, j - 1);
        }
        return 0;
    }
//
//    public int maxAreaOfIsland(int[][] grid) {
//        //方法2: DFS + 栈 7ms
//        int res = 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        for (int i = 0; i < grid.length; ++i) {
//            for (int j = 0; j < grid[0].length; ++j) {
//                int area = 0;
//                Deque<int[]> stack = new LinkedList<>();
//                stack.push(new int[]{i, j});
//                while (!stack.isEmpty()) {
//                    int[] cell = stack.pop();
//                    int r = cell[0];
//                    int c = cell[1]; //注意下面用的是 r c
//                    if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0)
//                        continue;
//                    grid[r][c] = 0;
//                    area++;
//                    for (int[] move : moves) {
//                        r = cell[0] + move[0];
//                        c = cell[1] + move[1];
//                        stack.push(new int[]{r, c});
//                    }
//                    //res = Math.max(res, area); //放在这里是错的
//                }
//                res = Math.max(res, area);
//            }
//        }
//        return res;
//    }

//    public int maxAreaOfIsland(int[][] grid) {
//        //方法3: BFS + 队列 3ms
//        //把方法2中的栈改为队列，每次从队首取出土地，并将接下来想要遍历的土地放在队尾
//        int res = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == 1) {
//                    res = Math.max(res, bfs(grid, i, j));
//                }
//            }
//        }
//        return res;
//    }
//
//    private int bfs(int[][] grid, int i, int j) { //3ms
//        int area = 1;
//        int m = grid.length;
//        int n = grid[0].length;
//        Queue<int[]> queue = new LinkedList<>();
//        queue.offer(new int[]{i, j});
//        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        grid[i][j] = 0;
//        while (!queue.isEmpty()) {
//            int[] cell = queue.remove();
//            for (int[] move : moves) {
//                int r = move[0] + cell[0];
//                int c = move[1] + cell[1];
//                if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
//                    grid[r][c] = 0;
//                    area++;
//                    queue.offer(new int[]{r, c});
//                }
//            }
//        }
//        return area;
//    }

//    public int maxAreaOfIsland(int[][] grid) {
//        //方法3: BFS + 队列 7ms
//        //把方法2中的栈改为队列，每次从队首取出土地，并将接下来想要遍历的土地放在队尾
//        int res = 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        for (int i = 0; i < grid.length; ++i) {
//            for (int j = 0; j < grid[0].length; ++j) {
//                int area = 0;
//                Queue<int[]> queue = new LinkedList<>();
//                queue.offer(new int[]{i, j});
//                while (!queue.isEmpty()) {
//                    int[] cell = queue.poll();
//                    int r = cell[0];
//                    int c = cell[1]; //注意下面用的是 r c
//                    if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0)
//                        continue;
//                    ++area;
//                    grid[r][c] = 0;
//                    for (int[] move : moves) {
//                        r = cell[0] + move[0];
//                        c = cell[1] + move[1];
//                        queue.offer(new int[]{r, c});
//                    }
//                } //while
//                res = Math.max(res, area);
//            }
//        }
//        return res;
//    }
}
