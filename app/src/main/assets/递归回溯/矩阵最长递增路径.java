package com.ng.code.menu.递归回溯;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/7a71a88cdf294ce6bdf54c899be967a2?tpId=295&tqId=1076860&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 描述
 * 给定一个 n 行 m 列矩阵 matrix ，矩阵内所有数均为非负整数。 你需要在矩阵中找到一条最长路径，使这条路径上的元素是递增的。并输出这条最长路径的长度。
 * 这个路径必须满足以下条件：
 * 1. 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外。
 * 2. 你不能走重复的单元格。即每个格子最多只能走一次。
 * 数据范围：1 \le n,m \le 10001≤n,m≤1000，0 \le matrix[i][j] \le 10000≤matrix[i][j]≤1000
 * 进阶：空间复杂度 O(nm)O(nm) ，时间复杂度 O(nm)O(nm)
 *
 * 示例:
 * 示例1
 * 输入：
 * [[1,2,3],[4,5,6],[7,8,9]]
 * 返回值：
 * 5
 *
 * 说明：
 * 1->2->3->6->9即可。当然这种递增路径不是唯一的。
 * 示例2
 * 输入：
 * [[1,2],[4,3]]
 * 返回值：
 * 4
 *
 * 说明：
 * 1->2->3->4
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 矩阵最长递增路径 {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        LogUtil.pring(EasySolution.solve(data));
    }

    /**
     * 对于矩阵中某一点，我们可以沿上下左右四个方向（边界情况除外）前进，在前进之后如果所在点的值小于等于上一点的值，
     * 那么说明此路径无效，返回上一点，并选取其他路径进行尝试。
     * 因为每个点都有可能是路径的头，所以需要对矩阵中的所有点为头进行查找。
     */
    private static class EasySolution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         * 递增路径的最大长度
         *
         * @param matrix int整型二维数组 描述矩阵的每个数
         * @return int整型
         */
        public static int solve(int[][] matrix) {
            int max = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    max = Math.max(max, dfs(matrix, i, j, -1));
                }
            }
            return max;
        }

        private static int dfs(int[][] mat, int i, int j, int pre) {
            if (mat[i][j] <= pre) {
                return 0;
            }
            int max = 0;
            if (i > 0) {
                max = Math.max(max, dfs(mat, i - 1, j, mat[i][j]));
            }
            if (j > 0) {
                max = Math.max(max, dfs(mat, i, j - 1, mat[i][j]));
            }
            if (i < mat.length - 1) {
                max = Math.max(max, dfs(mat, i + 1, j, mat[i][j]));
            }
            if (j < mat[i].length - 1) {
                max = Math.max(max, dfs(mat, i, j + 1, mat[i][j]));
            }
            return max + 1;
        }
    }


    /**
     * 如果我们已经知道以该点为头的最长递增路径长度，那么在dfs查找时可以直接使用这个长度，
     * 而无需再次计算，所以我们可以用一个矩阵将已经计算得到的最长递增路径chang
     */
    private static class HardSolution {
        private static int[][] maxMat;

        public static int solve(int[][] matrix) {
            maxMat = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    maxMat[i][j] = 0;
                }
            }
            int max = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    max = Math.max(max, dfs(matrix, i, j, -1));
                }
            }
            return max;
        }

        private static int dfs(int[][] mat, int i, int j, int pre) {
            if (mat[i][j] <= pre) {
                return 0;
            }
            if (maxMat[i][j] != 0) {
                return maxMat[i][j];
            }
            int max = 0;
            if (i > 0) {
                max = Math.max(max, dfs(mat, i - 1, j, mat[i][j]));
            }
            if (j > 0) {
                max = Math.max(max, dfs(mat, i, j - 1, mat[i][j]));
            }
            if (i < mat.length - 1) {
                max = Math.max(max, dfs(mat, i + 1, j, mat[i][j]));
            }
            if (j < mat[i].length - 1) {
                max = Math.max(max, dfs(mat, i, j + 1, mat[i][j]));
            }
            maxMat[i][j] = max + 1;
            return max + 1;
        }
    }

}
