package com.ng.code.menu.哈希;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/4sum-ii/
 * 原题描述:
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * <p>
 * 示例:
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * 输出：2
 * 解释：
 * 两个元组如下：
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 * 示例 2：
 * <p>
 * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * 输出：1
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅱ_四数相加 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{-2, -1};
        int[] nums3 = new int[]{-1, 2};
        int[] nums4 = new int[]{0, 2};

        LogUtil.print(hardSolution.fourSumCount(nums1, nums2, nums3, nums4));
    }

    /**
     * 思路与算法
     * 我们可以将四个数组分成两部分，A  和 B 为一组，C  和 D  为另外一组。
     * 对于 A  和 B ，我们使用二重循环对它们进行遍历，得到所有 A[i]+B[j] 的值并存入哈希映射中。
     * 对于哈希映射中的每个键值对，每个键表示一种 A[i]+B[j]，对应的值为  A[i]+B[j] 出现的次数。
     * 对于 C  和 D ，我们同样使用二重循环对它们进行遍历。当遍历到 C[k]+D[l] 时，如
     * 果  −(C[k]+D[l]) 出现在哈希映射中，那么将 −(C[k]+D[l]) 对应的值累加进答案中。
     * 最终即可得到满足 A[i]+B[j]+C[k]+D[l]=0 的四元组数目。
     */
    private static class EasySolution {

        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
            for (int u : A) {
                for (int v : B) {
                    countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
                }
            }
            int ans = 0;
            for (int u : C) {
                for (int v : D) {
                    if (countAB.containsKey(-u - v)) {
                        ans += countAB.get(-u - v);
                    }
                }
            }
            return ans;
        }
    }

    private static class HardSolution {

        /**
         * 击败99%用户
         * 概念上还是1 + 2 = 3 + 4
         * 1.先求出每个nums最小最大区间
         * 2.计算max = max (12最大值，-34最小值)
         * 3.计算min = min (12最小值，-34最大值)
         * 4.arr[]下标为 min ～ max，内容为次数
         * 5.遍历1，2，给arr[]指定下标添加次数
         * 6.遍历3，4，统计arr[]指定下标处的次数
         *
         * 相比较easy解法，这里主要是通过数组，来替代了map，提高效率。
         */
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            int[] max_min_1 = getMaxMin(nums1);
            int[] max_min_2 = getMaxMin(nums2);
            int[] max_min_3 = getMaxMin(nums3);
            int[] max_min_4 = getMaxMin(nums4);
            int res = 0;
            int max = Math.max(max_min_1[1] + max_min_2[1], -(max_min_3[0] + max_min_4[0]));
            int min = Math.min(max_min_1[0] + max_min_2[0], -(max_min_3[1] + max_min_4[1]));

            int[] arr = new int[max - min + 1];
            for (int i : nums1) {
                for (int j : nums2) {
                    arr[i + j - min]++;
                }
            }

            for (int i : nums3) {
                for (int j : nums4) {
                    res += arr[-(i + j) - min];
                }
            }
            return res;
        }

        private int[] getMaxMin(int[] nums) {
            int max = nums[0];
            int min = nums[0];
            for (int num : nums) {
                if (max < num) {
                    max = num;
                }
                if (min > num) {
                    min = num;
                }
            }
            return new int[]{min, max};
        }

    }

}
