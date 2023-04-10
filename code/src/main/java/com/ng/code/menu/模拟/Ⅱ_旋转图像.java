package com.ng.code.menu.模拟;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/rotate-image/
 * 原题描述:
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 示例:
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_旋转图像 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
          {1, 2, 3},
          {4, 5, 6},
          {7, 8, 9}
        };
        EasySolution.rotate(matrix);
        LogUtil.print(matrix);

    }

    private static class EasySolution {

        /**
         * 两次翻转
         * 先沿右上 - 左下的对角线翻转（270° +270°+ 一次镜像），
         * 再沿水平中线上下翻转（-180° +−180°+ 一次镜像），可以实现顺时针 90 度的旋转效果
         */

        public static void rotate(int[][] matrix) {
            if (matrix.length == 0 || matrix.length != matrix[0].length) {
                return;
            }
            int nums = matrix.length;
            /**
             * 1 2 3                 9 6 3
             * 4 5 6        ->       8 5 2
             * 7 8 9                 7 4 1
             */
            for (int i = 0; i < nums; ++i) {
                for (int j = 0; j < nums - i; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[nums - 1 - j][nums - 1 - i];
                    matrix[nums - 1 - j][nums - 1 - i] = temp;
                }
            }
            /**
             * 9 6 3                 7 4 1
             * 8 5 2        ->       8 5 2
             * 7 4 1                 9 6 3
             */
            for (int i = 0; i < (nums / 2); ++i) {
                for (int j = 0; j < nums; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[nums - 1 - i][j];
                    matrix[nums - 1 - i][j] = temp;
                }
            }

        }

    }

    private static class HardSolution {

        /**
         * 自外向内顺时针循环
         * 自外向内一共有不超过 n/2 层（单个中心元素不算一层）矩形框。
         * 对于第 times 层矩形框，其框边长 len=nums-(times*2)，
         * 将其顺时针分为 4 份 len-1len−1 的边，对四条边进行元素的循环交换即可。
         */
        public static void rotate(int[][] matrix) {
            if (matrix.length == 0 || matrix.length != matrix[0].length) {
                return;
            }
            int nums = matrix.length;
            int times = 0; //层
            while (times <= (nums / 2)) {
                int len = nums - (times * 2); // 第times层的边长
                for (int i = 0; i < len - 1; ++i) {
                    //循环交换
                    int temp = matrix[times][times + i];    //暂存第一个的值

                    /**
                     * 1 2 3                 7 4 1
                     * 4 5 6        ->       8 5 2
                     * 7 8 9                 9 6 3
                     */
                    matrix[times][times + i] = matrix[times + len - i - 1][times];  //1 -> 7
                    matrix[times + len - i - 1][times] = matrix[times + len - 1][times + len - i - 1];  //7 ->9
                    matrix[times + len - 1][times + len - i - 1] = matrix[times + i][times + len - 1];  //9 -> 3
                    matrix[times + i][times + len - 1] = temp;      //  3-> 1
                }
                ++times;
            }
        }
    }

}
