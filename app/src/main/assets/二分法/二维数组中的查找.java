package com.ng.code.menu.二分法;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=295&tqId=23256&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 在一个二维数组array中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 *
 * 给定 target = 3，返回 false。
 *
 * 数据范围：矩阵的长宽满足 0 <= n,m <= 5000≤n,m≤500 ， 矩阵中的值满足 0 <= val <= 10^90≤val≤10
 * 9
 *
 * 进阶：空间复杂度 O(1)O(1) ，时间复杂度 O(n+m)O(n+m)
 * 示例1
 * 输入：
 * 7,[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]
 * 复制
 * 返回值：
 * true
 * 复制
 * 说明：
 * 存在7，返回true
 */
@Solution(easy = 1, hard = 0)
public class 二维数组中的查找 {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        LogUtil.pring(HardSolution.Find(7, data));
    }

    //暴力法
    private static class EasySolution {
        public static boolean Find(int target, int[][] array) {
            if (array.length == 0 || array[0].length == 0) {
                return false;
            }
            for (int[] tempArray : array) {
                for (int temp : tempArray) {
                    if (temp == target) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    /**
     * 二分二维思想
     * 0  1  2  3  4  5
     * 0 <  <  <  <  <  var
     * 1                >
     * 2                >
     * 3   tar          >
     * 4                >
     * 1）我么设初始值为右上角元素，arr[0][5] = val，目标tar = arr[3][1]
     * 2）接下来进行二分操作：
     * 3）如果val == target,直接返回
     * 4）如果 tar > val, 说明target在更大的位置，val左边的元素显然都是 < val，间接 < tar，说明第 0 行都是无效的，所以val下移到arr[1][5]
     * 5）如果 tar < val, 说明target在更小的位置，val下边的元素显然都是 > val，间接 > tar，说明第 5 列都是无效的，所以val左移到arr[0][4]
     */
    private static class HardSolution {
        public static boolean Find(int target, int[][] array) {
            int yLength = array.length;
            if (yLength == 0) return false;
            int xLength = array[0].length;
            if (xLength == 0) return false;
            //从右上角开始遍历!
            int y = 0, x = xLength - 1;
            while (y < yLength && x >= 0) {
                if (target == array[y][x]) {
                    return true;
                } else if (target > array[y][x]) {
                    y++;
                } else {
                    --x;
                }

            }
            return false;
        }
    }


}
