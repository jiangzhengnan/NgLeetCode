package com.ng.code.menu.哈希;

import android.os.Build;

import androidx.annotation.RequiresApi;

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
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_四数相加 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

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
        @RequiresApi(api = Build.VERSION_CODES.N)
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

    }

}
