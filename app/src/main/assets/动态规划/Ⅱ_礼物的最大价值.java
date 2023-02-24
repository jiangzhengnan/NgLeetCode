package com.ng.code.menu.动态规划;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/2237b401eb9347d282310fc1c3adb134?tpId=13&tqId=2276652&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 在一个m\times nm×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 如输入这样的一个二维数组，
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 那么路径 1→3→5→2→1 可以拿到最多价值的礼物，价值为12
 * <p>
 * 示例:
 * 输入：
 * [[1,3,1],[1,5,1],[4,2,1]]
 * 返回值：
 * 12
 * <p>
 * Ⅱ_矩阵的最小路径和的镜像题
 */
@Solution(easy = 1, hard = 0, partice = 0)
public class Ⅱ_礼物的最大价值 {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {9, 1, 4, 8}
        };
        LogUtil.print(EasySolution.maxValue(data));
    }

    /**
     * 最长路径题
     * dp[i][j]  表示当前位置最大的值
     * i为行j为列
     * dp[i][j] = Math.max(dp[i-1][j] + grid[i][j] , dp[i][j-1] + grid[i][j] );
     */
    private static class EasySolution {

        public static int maxValue(int[][] grid) {
            // write code here
            int[][] dp = new int[grid.length + 1][grid[0].length + 1];
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1],
                            dp[i + 1][j]) + grid[i][j];
                }
            }
            return dp[grid.length][grid[0].length];

        }

    }

    //简化代码
    private static class HardSolution {

        public static int maxValue(int[][] grid) {
            int[][] dp = new int[grid.length + 1][grid[0].length + 1];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int x = i + 1, y = j + 1;
                    dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - 1]) + grid[i][j];
                }
            }
            LogUtil.print(dp);
            return dp[grid.length][grid[0].length];
        }

    }

}
