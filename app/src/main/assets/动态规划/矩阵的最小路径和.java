package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 描述
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 *
 * 数据范围: 1 \le n,m\le 5001≤n,m≤500，矩阵中任意值都满足 0 \le a_{i,j} \le 1000≤a
 * 要求：时间复杂度 O(nm)O(nm)
 *
 * 示例:
 * 示例1
 * 输入：
 * [[1,3,5,9],[8,1,3,4],[5,0,6,1],[8,8,4,0]]
 * 返回值：
 * 12
 *
 * 示例2
 * 输入：
 * [[1,2,3],[1,2,3]]
 * 返回值：
 * 7
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 矩阵的最小路径和 {

    public static void main(String[] args) {

    }

    /**
     * 动态规划
     * 这题求的是从左上角到右下角，路径上的数字和最小，并且每次只能向下或向右移动。所以上面很容易想到动态规划求解。
     * 我们可以使用一个二维数组dp，dp[i][j]表示的是从左上角到坐标(i，j)的最小路径和。
     * 那么走到坐标(i，j)的位置只有这两种可能，要么从上面(i-1，j)走下来，要么从左边（i，j-1）走过来，
     * 我们要选择路径和最小的再加上当前坐标的值就是到坐标（i，j）的最小路径。
     * 所以递推公式就是
     * dp[i][j]=min(dp[i-1][j]+dp[i][j-1])+grid[i][j];
     *
     * 有了递推公式再来看一下边界条件，当在第一行的时候，因为不能从上面走下来，
     * 所以当前值就是前面的累加。同理第一列也一样，因为他不能从左边走过来，所以当前值只能是上面的累加。
     */
    private static class EasySolution {
        public int minPathSum(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] dp = new int[m][n];
            dp[0][0] = matrix[0][0];
            //第一列只能从上面走下来
            for (int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + matrix[i][0];
            }
            //第一行只能从左边走过来
            for (int i = 1; i < n; i++) {
                dp[0][i] = dp[0][i - 1] + matrix[0][i];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    //递推公式，取从上面走下来和从左边走过来的最小值+当前坐标的值
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
                }
            }
            return dp[m - 1][n - 1];
        }
    }

    /**
     * 我们看到二维数组dp和二维数组matrix的长和宽都是一样的，没必要再申请一个dp数组，完全可以使用matrix，来看下代码
     */
    private static class HardSolution {
        public int minPathSum(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0)
                        continue;
                    if (i == 0) {
                        //第一行只能从左边走过来
                        matrix[i][j] += matrix[i][j - 1];
                    } else if (j == 0) {
                        //第一列只能从上面走下来
                        matrix[i][j] += matrix[i - 1][j];
                    } else {
                        //递推公式，取从上面走下来和从左边走过来的最小值+当前坐标的值
                        matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i][j - 1]);
                    }
                }
            }
            return matrix[m - 1][n - 1];
        }
    }

}
