package com.ng.code.menu.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给出一组区间，请合并所有重叠的区间。
 * 请保证合并后的区间按区间起点升序排列。
 * <p>
 * 要求：空间复杂度 O(n) ，时间复杂度 O(nlogn)
 * 进阶：空间复杂度 O(val) 时间复杂度O(val)
 * <p>
 * 示例:
 * 输入：
 * [[10,30],[20,60],[80,100],[150,180]]
 * 返回值：
 * [[10,60],[80,100],[150,180]]
 */
@Solution(easy = 0, hard = 1, partice = 0)
public class Ⅱ_合并区间 {

    public static void main(String[] args) {
        int[][] data = new int[][]{
          {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };

        LogUtil.pring(EasySolution.merge(data));
        //[[1,6],[8,10],[15,18]]

    }

    /**
     * 自己的解法，双重循环
     * 每一次从结果集里去找有没有重叠的
     */
    private static class EasySolution {
        static ArrayList<int[]> result = new ArrayList<>();

        public static int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(final int[] o1, final int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            for (int[] input : intervals) {

                boolean needAdd = true;
                for (int i = 0; i < result.size(); i++) {
                    int[] temp = result.get(i);
                    if (input[1] < temp[0] || input[0] > temp[1]) {
                        continue;
                    } else {
                        temp[0] = Math.min(temp[0], input[0]);
                        temp[1] = Math.max(temp[1], input[1]);
                        needAdd = false;
                    }
                }
                if (needAdd) {
                    result.add(input);
                }

            }


            return result.toArray(new int[0][]);
        }
    }

    /**
     * 1.如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，
     * 直接将这个区间加入数组 merged 的末尾；
     * 2.否则，它们重合，需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
     */
    private static class HardSolution {

        public int[][] merge(int[][] intervals) {
            // 先按照区间起始位置排序
            Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
            // 遍历区间
            int[][] res = new int[intervals.length][2];
            int idx = -1;
            for (int[] interval : intervals) {
                // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
                // 则不合并，直接将当前区间加入结果数组。
                if (idx == -1 || interval[0] > res[idx][1]) {
                    res[++idx] = interval;
                } else {
                    // 反之将当前区间合并至结果数组的最后区间
                    res[idx][1] = Math.max(res[idx][1], interval[1]);
                }
            }
            return Arrays.copyOf(res, idx + 1);
        }
    }

}
