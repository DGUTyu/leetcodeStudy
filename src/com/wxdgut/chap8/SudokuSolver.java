package com.wxdgut.chap8;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-23 19:42:45
 * <p>
 * 37. 解数独 难度：困难
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * <p>
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 */

public class SudokuSolver {

//    public void solveSudoku(char[][] board) {
//        //方法1：回溯 两个for循环嵌套着递归 7ms
//        solveSudokuHelper(board);
//    }
//
//    private boolean solveSudokuHelper(char[][] board) {
//        //一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
//        //一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！
//        for (int i = 0; i < 9; i++) { // 遍历行
//            for (int j = 0; j < 9; j++) { // 遍历列
//                if (board[i][j] != '.') continue;// 跳过原始数字
//                for (char k = '1'; k <= '9'; k++) { // (i, j) 这个位置放k是否合适
//                    if (isValid(i, j, k, board)) {
//                        board[i][j] = k;
//                        // 如果找到合适一组立刻返回
//                        if (solveSudokuHelper(board)) return true;
//                        board[i][j] = '.';
//                    }
//                }
//                return false; // 9个数都试完了，都不行，那么就返回false
//                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
//                // 那么会直接返回，这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！
//            }
//        }
//        return true; // 遍历完没有返回false，说明找到了合适棋盘位置了
//    }
//
//    //判断棋盘是否合法有如下三个维度: 同行/同列/9宫格里是否重复
//    private boolean isValid(int row, int col, char val, char[][] board) {
//        for (int i = 0; i < 9; i++) {
//            if (board[row][i] == val) return false;
//            if (board[i][col] == val) return false;
//        }
//        int startRow = (row / 3) * 3;
//        int startCol = (col / 3) * 3;
//        for (int i = startRow; i < startRow + 3; i++) {
//            for (int j = startCol; j < startCol + 3; j++) {
//                if (board[i][j] == val) return false;
//            }
//        }
//        return true;
//    }

    public void solveSudoku(char[][] board) {
        //方法1：回溯版本2 4ms
        solve(board);
    }

    boolean solve(char[][] board) {
        int row = -1;
        int col = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    row = i;
                    col = j;
                    break;
                }
            }
        }
        if (row == -1 && col == -1) return true;
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(row, col, c, board)) {
                board[row][col] = c;
                if (solve(board)) return true;
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, char val, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) return false;
            if (board[i][col] == val) return false;
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) return false;
            }
        }
        return true;
    }

//    private boolean isValidB(int row, int col, char ch, char[][] board) {
//        for (int i = 0; i < 9; i++) {
//            if (board[row][i] == ch) return false;
//            if (board[i][col] == ch) return false;
//        }
//        int r = row / 3 * 3;
//        int c = col / 3 * 3;
//        for (int i = 0; i < 9; i++) {
//            if (board[r + i / 3][c + i % 3] == ch) return false;
//        }
//        return true;
//    }

//    //储存每一行/列/每一个 3*3宫 存在的数字
//    int[] rawSet = new int[9], colSet = new int[9], boxSet = new int[9];
//    int[] bitSet = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256};
//
//    public void solveSudoku(char[][] board) {
//        //方法2：回溯 Java bit set 1ms
//        int[][] emptyList = new int[81][3];
//        int listSize = 0;
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                int box = ((j / 3) * 3) + (i / 3);
//                if (board[i][j] == '.') {
//                    emptyList[listSize++] = new int[]{i, j, box};
//                } else {
//                    int bit = bitSet[board[i][j] - '1'];
//                    rawSet[i] |= bit;
//                    colSet[j] |= bit;
//                    boxSet[box] |= bit;
//                }
//            }
//        }
//        dfs(emptyList, listSize, 0, board);
//    }
//
//    boolean dfs(int[][] list, int listSize, int idx, char[][] board) {
//        if (idx >= listSize) return true;
//        int[] cur = list[idx];
//        int set = rawSet[cur[0]] | colSet[cur[1]] | boxSet[cur[2]];
//        for (int i = 1, d = 1; i <= 9; i++, d <<= 1) {
//            if ((set & d) == 0) {
//                rawSet[cur[0]] |= d;
//                colSet[cur[1]] |= d;
//                boxSet[cur[2]] |= d;
//                if (dfs(list, listSize, idx + 1, board)) {
//                    board[cur[0]][cur[1]] = (char) ('0' + i);
//                    return true;
//                }
//                rawSet[cur[0]] ^= d;
//                colSet[cur[1]] ^= d;
//                boxSet[cur[2]] ^= d;
//            }
//        }
//        return false;
//    }

}
