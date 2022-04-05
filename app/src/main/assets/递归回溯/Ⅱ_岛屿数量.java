package com.ng.code.menu.递归回溯;

import android.util.Log;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/0c9664d1554e466aa107d899418e814e?tpId=295&tqId=1024684&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 描述
 * 给一个01矩阵，1代表是陆地，0代表海洋， 如果两个1相邻，那么这两个1属于同一个岛。我们只考虑上下左右为相邻。
 * 岛屿: 相邻陆地可以组成一个岛屿（相邻:上下左右） 判断岛屿个数。
 * 例如：
 * 输入
 * [
 * [1,1,0,0,0],
 * [0,1,0,1,1],
 * [0,0,0,1,1],
 * [0,0,0,0,0],
 * [0,0,1,1,1]
 * ]
 * 对应的输出为3
 * (注：存储的01数据其实是字符'0','1')
 *
 * 示例:
 * 示例1
 * 输入：
 * [[1,1,0,0,0],[0,1,0,1,1],[0,0,0,1,1],[0,0,0,0,0],[0,0,1,1,1]]
 * 返回值：
 * 3
 *
 * 示例2
 * 输入：
 * [[0]]
 * 返回值：
 * 0
 *
 * 示例3
 * 输入：
 * [[1,1],[1,1]]
 * 返回值：
 * 1
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_岛屿数量 {

    public static void main(String[] args) {
        char[][] data = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'0', '1', '0', '1', '1'},
                {'0', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '1', '1', '1'}
        };
        LogUtil.pring(EasySolution.solve(data));
    }

    /**
     * dfs深度优先遍历
     * 遍历数组中的每一个值，如果是1就说明是岛屿，然后把它置为0或者其他的字符都可以，
     * 只要不是1就行，然后再遍历他的上下左右4个位置。如果是1，说明这两个岛屿是连着的，
     * 只能算是一个岛屿，我们还要把它置为0，然后再以它为中心遍历他的上下左右4个位置……。
     * 如果是0，就说明不是岛屿，就不在往他的上下左右4个位置遍历了。这里就以示例1为例来看一下
     */
    private static class EasySolution {

        public static int solve(char[][] grid) {
            //边界条件判断
            if (grid == null || grid.length == 0)
                return 0;
            //统计岛屿的个数
            int count = 0;
            //两个for循环遍历每一个格子
            for (int i = 0; i < grid.length; i++)
                for (int j = 0; j < grid[0].length; j++) {
                    //只有当前格子是1才开始计算
                    if (grid[i][j] == '1') {
                        //如果当前格子是1，岛屿的数量加1
                        count++;
                        //然后通过dfs把当前格子的上下左右4
                        //个位置为1的都要置为0，因为他们是连着
                        //一起的算一个岛屿，
                        dfs(grid, i, j);
                    }
                }
            //最后返回岛屿的数量
            return count;
        }

        //这个方***把当前格子以及他邻近的为1的格子都会置为1
        public static void dfs(char[][] grid, int i, int j) {
            //边界条件判断，不能越界
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
                return;
            //把当前格子置为0，然后再从他的上下左右4个方向继续遍历
            grid[i][j] = '0';
            dfs(grid, i - 1, j);//上
            dfs(grid, i + 1, j);//下
            dfs(grid, i, j + 1);//左
            dfs(grid, i, j - 1);//右
        }

    }

    private static class HardSolution {
    }

}
