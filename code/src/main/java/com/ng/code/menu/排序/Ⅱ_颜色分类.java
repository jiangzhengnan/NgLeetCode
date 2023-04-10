package com.ng.code.menu.排序;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/sort-colors/
 * 原题描述:
 * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库的sort函数的情况下解决这个问题。
 * 示例:
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_颜色分类 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        int[] data = new int[]{2,0,2,1,1,0};
        hardSolution.sortColors(data);
        LogUtil.print(data);
    }

    private static class EasySolution {

    }

    private static class HardSolution {
        public void sortColors(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return;
            }

            // all in [0, zero) = 0
            // all in [zero, i) = 1
            // all in [two, len - 1] = 2

            // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
            // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
            // 所以下面遍历到 0 的时候，先交换，再加
            int zero = 0;

            // 为了保证初始化的时候 [two, len - 1] 为空，设置 two = len
            // 所以下面遍历到 2 的时候，先减，再交换
            int two = len;
            int i = 0;
            // 当 i == two 上面的三个子区间正好覆盖了全部数组
            // 因此，循环可以继续的条件是 i < two
            while (i < two) {
                if (nums[i] == 0) {
                    swap(nums, i, zero);
                    zero++;
                    i++;
                } else if (nums[i] == 1) {
                    i++;
                } else {
                    two--;
                    swap(nums, i, two);
                }
            }
        }

        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }

}
