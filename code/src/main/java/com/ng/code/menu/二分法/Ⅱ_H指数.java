package com.ng.code.menu.二分法;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/h-index/description/?envType=study-plan-v2&envId=top
 * -interview-150
 * 原题描述:
 * 提示
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。
 * 计算并返回该研究者的 h 指数。
 * <p>
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
 * 并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h
 * 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * 示例 1：
 * <p>
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * 示例 2：
 * <p>
 * 输入：citations = [1,3,1]
 * 输出：1
 * <p>
 * 提示：
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
@Solution(easy = 1, hard = 0, particle = 1)
public class Ⅱ_H指数 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {
        /**
         * 简单直观解法，不推荐，n2 复杂度
         */
        public int hIndex(int[] citations) {
            int result = 0;
            int[] tong = new int[citations.length];
            for (int citation : citations) {
                int temp = Math.min(citation, citations.length);
                for (int j = 0; j < temp; j++) {
                    tong[j]++;
                    if (tong[j] == (j + 1) && tong[j] > result) {
                        result = tong[j];
                    }
                }
            }
            return result;
        }
    }

    private static class HardSolution {


        /**
         * 二分搜索
         * 我们需要找到一个值 h，它是满足「有 h 篇论文的引用次数至少为 h」的最大值。小于等于 h 的所有值 x 都满足这个性质，而大于 h
         * 的值都不满足这个性质。同时因为我们可以用较短时间（扫描一遍数组的时间复杂度为 O(n)，其中 n 为数组 citations 的长度）
         * 来判断 x 是否满足这个性质，所以这个问题可以用二分搜索来解决。
         * 设查找范围的初始左边界 left 为 0，初始右边界 right 为 n。每次在查找范围内取中点
         * mid，同时扫描整个数组，判断是否至少有 mid 个数大于 mid。如果有，说明要寻找的 h 在搜索区间的右边，反之则在左边。
         */
        public int hIndex(int[] citations) {
            int left = 0, right = citations.length;
            int mid = 0, cnt = 0;
            while (left < right) {
                // +1 防止死循环
                mid = (left + right + 1) >> 1;      //关键是mid的计算。二分的h值范围，每次搜寻还是从遍历整个数组，数组排不排序无所谓
                cnt = 0;
                for (int i = 0; i < citations.length; i++) {
                    if (citations[i] >= mid) {
                        cnt++;
                    }
                }
                if (cnt >= mid) {
                    // 要找的答案在 [mid,right] 区间内
                    left = mid;
                } else {
                    // 要找的答案在 [0,mid) 区间内
                    right = mid - 1;
                }
            }
            return left;
        }
    }

}
