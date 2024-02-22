package com.ng.code.menu.贪心算法;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 描述
 * 一群孩子做游戏，现在请你根据游戏得分来发糖果，要求如下：
 * 1. 每个孩子不管得分多少，起码分到一个糖果。
 * 2. 任意两个相邻的孩子之间，得分较多的孩子必须拿多一些糖果。(若相同则无此限制)
 * 给定一个数组 arrarr 代表得分数组，请返回最少需要多少糖果。
 * 要求: 时间复杂度为 O(n)O(n) 空间复杂度为 O(n)O(n)
 *
 * 示例:
 * 示例1
 * 输入：
 * [1,1,2]
 * 返回值：
 * 4
 * 说明：
 * 最优分配方案为1,1,2
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_分糖果问题 {

    public static void main(String[] args) {

    }

    /**
     * Step1:首先从左到右遍历，利用贪心的思想，如果右边的孩子比左边孩子的分数高，那么就多的一颗糖。
     * Step2：再从右向左遍历，如果左边的孩子比右边孩子的得分高，则孩子的糖数更新为max(左边孩子的糖果数，右边孩子的糖果数+1)，最终得到答案。
     * 时间复杂度：一层循环，因此时间复杂度为O(N)。
     * 空间复杂度：使用了vector candy，因此空间复杂度为O(N)，N为candy的大小。
     */
    private static class EasySolution {

        public int candy(int[] arr) {
            // 从左往后、从右往左各遍历一次
            int[] candys = new int[arr.length];
            int sum = 0;
            for (int i = 0; i < candys.length; i++) {
                candys[i] = 1;
            }

            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] < arr[i + 1]) {
                    candys[i + 1] = candys[i] + 1;
                }
            }
            for (int i = arr.length - 1; i > 0; i--) {
                if (arr[i - 1] > arr[i] && candys[i - 1] <= candys[i]) {
                    candys[i - 1] = candys[i] + 1;
                }
            }
            for (int i : candys)
                sum += i;
            return sum;
        }


    }

    private static class HardSolution {

    }

}
