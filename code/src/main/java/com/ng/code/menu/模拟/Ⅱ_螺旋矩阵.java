package com.ng.code.menu.模拟;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给定一个m x n大小的矩阵（m行，n列），按螺旋的顺序返回矩阵中的所有元素。
 * 数据范围：0 \le n,m \le 100≤n,m≤10，矩阵中任意元素都满足 |val| \le 100∣val∣≤100
 * 要求：空间复杂度 O(nm)O(nm) ，时间复杂度 O(nm)O(nm)
 *
 * 示例:
 * 示例1
 * 输入：
 * [[1,2,3],[4,5,6],[7,8,9]]
 * 返回值：
 * [1,2,3,6,9,8,7,4,5]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_螺旋矩阵 {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        LogUtil.print(EasySolution.spiralOrder(data).toString());
    }

    //按照从左到右、从上到下、从右到左、从下到上的顺序依此遍历。
    private static class EasySolution {

        public static ArrayList<Integer> spiralOrder(int[][] matrix) {
            ArrayList<Integer> list = new ArrayList<>();

            if (matrix.length == 0) {
                return list;
            }

            int left = 0;
            int right = matrix[0].length - 1;
            int top = 0;
            int bottom = matrix.length - 1;
            int x = 0;


            while (true) {
                for (int i = left; i <= right; i++) {  //从左到右
                    list.add(matrix[top][i]);
                }

                if (++top > bottom) {
                    break;
                }
                for (int i = top; i <= bottom; i++) {
                    list.add(matrix[i][right]);    //从上到下
                }

                if (left > --right) {
                    break;
                }
                for (int i = right; i >= left; i--) {
                    list.add(matrix[bottom][i]); //从右到左
                }

                if (top > --bottom) {
                    break;
                }
                for (int i = bottom; i >= top; i--) {
                    list.add(matrix[i][left]);   //从下到上
                }

                if (++left > right) {
                    break;
                }
            }
            return list;
        }
    }

    private static class HardSolution {

    }

}
