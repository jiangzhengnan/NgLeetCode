package com.ng.code.menu.模拟;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 有一个nxn整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
 *
 * 给定一个nxn的矩阵，和矩阵的阶数n,请返回旋转后的nxn矩阵。
 *
 * 数据范围：0 < n < 3000<n<300，矩阵中的值满足 0 \le val \le 10000≤val≤1000
 *
 * 要求：空间复杂度 O(n^2) 时间复杂度 O(n^2)
 * 进阶：空间复杂度 O(1) ，时间复杂度 O(n^2)
 *
 * 示例:
 * 示例1
 * 输入：
 * [[1,2,3],[4,5,6],[7,8,9]],3
 * 返回值：
 * [[7,4,1],[8,5,2],[9,6,3]]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_顺时针旋转矩阵 {

    public static void main(String[] args) {

    }

    /**
     * 这题是让把矩阵顺时针旋转90度，最简单的一种方式就是先上下关于中心线翻转，然后再对角线翻转，具体看下图形分析
     */
    private static class EasySolution {

        public int[][] rotateMatrix(int[][] matrix, int n) {
            int length = matrix.length;
            //先上下交换
            for (int i = 0; i < length / 2; i++) {
                int temp[] = matrix[i];
                matrix[i] = matrix[length - i - 1];
                matrix[length - i - 1] = temp;
            }
            //在按照对角线交换
            for (int i = 0; i < length; ++i) {
                for (int j = i + 1; j < length; ++j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
            return matrix;
        }

    }

    private static class HardSolution {

    }

}
