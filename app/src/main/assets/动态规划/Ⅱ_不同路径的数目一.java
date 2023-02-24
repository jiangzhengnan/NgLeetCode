package com.ng.code.menu.动态规划;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * https://www.nowcoder.com/practice/166eaff8439d4cd898e3ba933fbc6358?tpId=295&tqId=685&ru=%2Fpractice%2Ffe6b651b66ae47d7acce78ffdd9a96c7&qru=%2Fta%2Fformat-top101%2Fquestion-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 一个机器人在m×n大小的地图的左上角（起点）。
 * 机器人每次可以向下或向右移动。机器人要到达地图的右下角（终点）。
 * 可以有多少种不同的路径从起点走到终点？
 * 备注：m和n小于等于100,并保证计算结果在int范围内
 * 数据范围：0 < n,m \le 1000<n,m≤100，保证计算结果在32位整型范围内
 * 要求：空间复杂度 O(nm)O(nm)，时间复杂度 O(nm)O(nm)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(min(n,m))O(min(n,m))
 *
 * 示例:
 * 示例1
 * 输入：
 * 2,1
 * 返回值：
 * 1
 *
 * 示例2
 * 输入：
 * 2,2
 * 返回值：
 * 2
 */
@Solution(easy = 1, hard = 0, partice = 2)
public class Ⅱ_不同路径的数目一 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.uniquePaths(2, 5));
    }

    /**
     * 经典使用dp数组
     * 我们定义一个二维dp数组，dp[i] [j] 表示从起点到我当前位置（i行j列）有多少条可行的路径，
     * 因为我们从约束条件中可以知道，机器人每次只能往右或者往下走，则我们当前点必定是从上面或者左边走过来的（除边界外）。
     * 当然，如果在地图的最上面或者最左边的话，最上面的点只能是从左边走到的，最左边的点只能是从上面走到的。
     * 则我们可以定义状态方程：
     *
     * 当 i > 1 && j > 1 : dp[i] [j] = dp[i] [j-1] + dp[i-1] [j]
     * 当 i = 0：dp[0] [j] = dp[0] [j-1]
     * 当 j = 0：dp[i] [0] = dp[i-1] [0]
     */
    private static class EasySolution {

        public static int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            LogUtil.pring(dp);

            return dp[m - 1][n - 1];
        }
    }

    /**
     * 递归
     */
    private static class HardSolution {

        public int uniquePaths (int m, int n) {
            // 起点（1，1） 这里为什么是（1，1）呢？其实是一样的，我们上面的方法定义了（0，0）为起点，那么终点就为（m-1，n-1）
            // 这里起点为（1，1）那么终点就为 （m，n）
            if(m == 1 || n == 1)
                return 1;
            // 终点（m，n）
            return uniquePaths(m,n-1)+uniquePaths(m-1,n);
        }

    }

}
