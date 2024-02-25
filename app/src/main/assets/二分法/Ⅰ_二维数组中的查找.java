package com.ng.code.menu.二分法;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 * m*n 的二维数组 plants 记录了园林景观的植物排布情况，具有以下特性：
 * 每行中，每棵植物的右侧相邻植物不矮于该植物；
 * 每列中，每棵植物的下侧相邻植物不矮于该植物。
 * <p>
 * 请判断 plants 中是否存在目标高度值 target。
 * 示例 1：
 * 输入：plants = [[2,3,6,8],[4,5,8,9],[5,9,10,12]], target = 8
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：plants = [[1,3,5],[2,5,7]], target = 4
 * 输出：false
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_二维数组中的查找 {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        LogUtil.print(HardSolution.Find(7, data));
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
