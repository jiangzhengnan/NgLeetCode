package com.ng.code.menu.递归回溯;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/solutions/346614/ju-zhen
 * -zhong-de-zui-chang-di-zeng-lu-jing-by-le-2/
 * 原题描述:
 * 描述
 * 给定一个 n 行 m 列矩阵 matrix ，矩阵内所有数均为非负整数。 你需要在矩阵中找到一条最长路径，使这条路径上的元素是递增的。并输出这条最长路径的长度。
 * 这个路径必须满足以下条件：
 * 1. 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外。
 * 2. 你不能走重复的单元格。即每个格子最多只能走一次。
 * 数据范围：1 \le n,m \le 10001≤n,m≤1000，0 \le matrix[i][j] \le 10000≤matrix[i][j]≤1000
 * 进阶：空间复杂度 O(nm)O(nm) ，时间复杂度 O(nm)O(nm)
 * 示例:
 * 示例1
 * 输入：
 * [[1,2,3],[4,5,6],[7,8,9]]
 * 返回值：
 * 5
 * 说明：
 * 1->2->3->6->9即可。当然这种递增路径不是唯一的。
 * 示例2
 * 输入：
 * [[1,2],[4,3]]
 * 返回值：
 * 4
 * 说明：
 * 1->2->3->4
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_矩阵最长递增路径 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        int[][] data = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        };
        LogUtil.print(easySolution.longestIncreasingPath(data));
    }

    /**
     * 记忆化dfs
     */
    private static class EasySolution {

        int[][] directs = new int[][]{
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1}
        };
        private static int[][] maxMat;

        public int longestIncreasingPath(int[][] matrix) {
            maxMat = new int[matrix.length][matrix[0].length];
            int res = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    res = Math.max(res, dfs(matrix, i, j));
                }
            }
            return res;
        }

        public int dfs(int[][] matrix, int row, int col) {
            if (maxMat[row][col] != 0) {
                return maxMat[row][col];
            }
            int max = 1;
            int x;
            int y;
            for (int i = 0; i < directs.length; i++) {
                x = row + directs[i][0];
                y = col + directs[i][1];
                if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                    continue;
                }
                if (matrix[row][col] < matrix[x][y]) {
                    max = Math.max(max, dfs(matrix, x, y) + 1);
                }
            }
            maxMat[row][col] = max;
            return max;
        }
    }



}
