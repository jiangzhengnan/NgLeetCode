package com.ng.code.menu.递归回溯;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/2a49359695a544b8939c77358d29b7e6?tpId=13&tqId=1517966&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 请设计一个函数，用来判断在一个n乘m的矩阵中是否存在一条包含某长度为len的字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如 \begin{bmatrix} a & b & c &e \\ s & f & c & s \\ a & d & e& e\\ \end{bmatrix}\quad
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * 示例:
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_矩阵中的路径 {

    public static void main(String[] args) {
        char[][] data = new char[][]{
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'},
        };
        LogUtil.print(EasySolution.hasPath(data, "abcced"));
    }

    private static class EasySolution {

        public static boolean hasPath(char[][] matrix, String word) {
            char[] words = word.toCharArray();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    //从[i,j]这个坐标开始查找
                    if (dfs(matrix, words, i, j, 0))
                        return true;
                }
            }
            return false;
        }

        static boolean dfs(char[][] matrix, char[] word, int i, int j, int index) {
            //边界的判断，如果越界直接返回false。index表示的是查找到字符串word的第几个字符，
            //如果这个字符不等于matrix[i][j]，说明验证这个坐标路径是走不通的，直接返回false
            if (i >= matrix.length || i < 0 || j >= matrix[0].length || j < 0 || matrix[i][j] != word[index])
                return false;
            //如果word的每个字符都查找完了，直接返回true
            if (index == word.length - 1)
                return true;
            //把当前坐标的值保存下来，为了在最后复原
            char tmp = matrix[i][j];
            //然后修改当前坐标的值
            matrix[i][j] = '.';
            //走递归，沿着当前坐标的上下左右4个方向查找
            boolean res = dfs(matrix, word, i + 1, j, index + 1)
                    || dfs(matrix, word, i - 1, j, index + 1)
                    || dfs(matrix, word, i, j + 1, index + 1)
                    || dfs(matrix, word, i, j - 1, index + 1);
            //递归之后再把当前的坐标复原
            matrix[i][j] = tmp;
            return res;
        }

    }

    private static class HardSolution {

    }

}
