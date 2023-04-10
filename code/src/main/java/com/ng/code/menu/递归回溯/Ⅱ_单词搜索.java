package com.ng.code.menu.递归回溯;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/word-search/
 * 原题描述:
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_单词搜索 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();

        char[][] stringsData = new char[][]{
          {'a', 'b'},
          {'c', 'd'},
          };
        char[][] stringsData2 = new char[][]{
          {'A', 'B', 'C', 'E'},
          {'S', 'F', 'C', 'S'},
          {'A', 'D', 'E', 'F'}
        };
        LogUtil.print(easySolution.exist(stringsData2, "ABCCED"));
    }

    /**
     * 自己做出来的方式。。勉强ac
     * 回溯+记录位置矩阵完成
     */
    private static class EasySolution {
        public String target;

        public boolean res;
        // 0 1 2 3 左 上 右 下
        public int direction = 0;
        public int[] directions = new int[]{1, -1, 2, -2};

        public boolean exist(char[][] board, String word) {
            target = word;
            char first = word.charAt(0);
            StringBuilder tempSb = new StringBuilder();
            tempSb.append(first);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (first == board[i][j]) {

                        char[][] tempBoard = getCopy(board);
                        tempBoard[i][j] = '0';
                        startLoop(tempBoard, tempSb, j, i, 0);
                    }
                }
            }
            return res;
        }

        private char[][] getCopy(final char[][] board) {
            char[][] res = new char[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    res[i][j] = board[i][j];
                }
            }
            return res;
        }

        private void startLoop(final char[][] board, StringBuilder tempSb, int x, int y, int lastF) {
            if (!target.substring(0, tempSb.length()).equals(tempSb.toString()) || res) {
                return;
            }

            if (target.equals(tempSb.toString())) {
                res = true;
                return;
            }

            for (int i = 0; i < directions.length; i++) {
                int tempX = x;
                int temY = y;
                if (directions[i] == -lastF) {
                    continue;
                }
                switch (directions[i]) {
                    case 1:
                        tempX++;
                        break;
                    case -1:
                        tempX--;
                        break;
                    case 2:
                        temY++;
                        break;
                    case -2:
                        temY--;
                        break;
                }
                if (tempX < 0 || tempX >= board[0].length || temY < 0 || temY >= board.length) {
                    continue;
                }
                char tempChar = board[temY][tempX];
                board[temY][tempX] = '0';

                tempSb.append(tempChar);

                startLoop(board, tempSb, tempX, temY, directions[i]);

                int nowIndex = tempSb.length();
                tempSb.delete(nowIndex - 1, nowIndex);
                board[temY][tempX] = tempChar;
            }

        }
    }


    /**
     * 设函数 check(i,j,k) 表示判断以网格的 (i,j) 位置出发，能否搜索到单词 word[k..]，其中 word[k..]
     * 表示字符串 word 从第 k 个字符开始的后缀子串。如果能搜索到，则返回 true，反之返回 false。
     * 函数 check(i,j,k) 的执行步骤如下：
     * <p>
     * 1.如果 board[i][j]!= s[k] 当前字符不匹配，直接返回false
     * 2.如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回 true
     * 3.否则，遍历当前位置的所有相邻位置。如果从某个相邻位置出发，能够搜索到子串 word[k+1..]，则返回 true，否则返回 false。
     * 这样，我们对每一个位置 (i,j) 都调用函数 check(i,j,0) 进行检查：只要有一处返回  true，就说明网格中能够找到相应的单词，否则说明不能找到。
     * <p>
     * 为了防止重复遍历相同的位置，需要额外维护一个与  board 等大的  visited 数组，用于标识每个位置是否被访问过。每次遍历相邻位置时，需要跳过已经被访问的位置。
     */
    private static class HardSolution {

        public boolean exist(char[][] board, String word) {
            int h = board.length, w = board[0].length;
            boolean[][] visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    boolean flag = check(board, visited, i, j, word, 0);
                    if (flag) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean check(char[][] board, boolean[][] visited, int i, int j, String s, int k) {
            if (board[i][j] != s.charAt(k)) {
                return false;
            } else if (k == s.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            boolean result = false;
            for (int[] dir : directions) {
                int newi = i + dir[0], newj = j + dir[1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        boolean flag = check(board, visited, newi, newj, s, k + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            visited[i][j] = false;
            return result;
        }
    }

}
