package com.ng.code.menu.模拟;

import java.util.LinkedList;
import java.util.Queue;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/surrounded-regions/
 * 原题描述:
 * 本题是深度优先搜索和广度优先搜索，以及坐标系选取的经典范例
 * <p>
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，
 * 并将这些区域里所有的 'O' 用 'X' 填充。
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 示例:
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_被围绕的区域 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        char[][] board = new char[][]{
          {'X', 'X', 'X', 'X'},
          {'X', 'O', 'O', 'X'},
          {'X', 'X', 'O', 'X'},
          {'X', 'O', 'X', 'X'},
          };

        easySolution.solve(board);
        LogUtil.print(board);
    }

    /**
     * 深度优先搜索
     */
    private static class EasySolution {
        int n, m;

        public void solve(char[][] board) {
            n = board.length;
            if (n == 0) {
                return;
            }
            m = board[0].length;
            for (int i = 0; i < n; i++) {
                dfs(board, i, 0);
                dfs(board, i, m - 1);
            }
            for (int i = 1; i < m - 1; i++) {
                dfs(board, 0, i);
                dfs(board, n - 1, i);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        public void dfs(char[][] board, int x, int y) {
            if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
                return;
            }
            board[x][y] = 'A';
            dfs(board, x + 1, y);
            dfs(board, x - 1, y);
            dfs(board, x, y + 1);
            dfs(board, x, y - 1);
        }
    }

    private static class HardSolution {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        public void solve(char[][] board) {
            int n = board.length;
            if (n == 0) {
                return;
            }
            int m = board[0].length;
            Queue<int[]> queue = new LinkedList<int[]>();
            for (int i = 0; i < n; i++) {
                if (board[i][0] == 'O') {
                    queue.offer(new int[]{i, 0});
                    board[i][0] = 'A';
                }
                if (board[i][m - 1] == 'O') {
                    queue.offer(new int[]{i, m - 1});
                    board[i][m - 1] = 'A';
                }
            }
            for (int i = 1; i < m - 1; i++) {
                if (board[0][i] == 'O') {
                    queue.offer(new int[]{0, i});
                    board[0][i] = 'A';
                }
                if (board[n - 1][i] == 'O') {
                    queue.offer(new int[]{n - 1, i});
                    board[n - 1][i] = 'A';
                }
            }
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; i++) {
                    int mx = x + dx[i], my = y + dy[i];
                    if (mx < 0 || my < 0 || mx >= n || my >= m || board[mx][my] != 'O') {
                        continue;
                    }
                    queue.offer(new int[]{mx, my});
                    board[mx][my] = 'A';
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    } else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

}
