package com.ng.code.menu.哈希;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/50ec6a5b0e4e45348544348278cdcee5?tpId=295&tqId=2188893&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 描述
 * 给定一个无重复元素的整数数组nums，请你找出其中没有出现的最小的正整数
 * 进阶： 空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 数据范围:
 * -231<=nums[i]<=231-1
 * 0<=len(nums)<=5*105
 *
 * 示例:
 * 示例1
 * 输入：
 * [1,0,2]
 * 返回值：
 * 3
 *
 * 示例2
 * 输入：
 * [-2,3,4,1,5]
 * 返回值：
 * 2
 *
 * 示例3
 * 输入：
 * [4,5,6,8,9]
 * 返回值：
 * 1
 */
@Solution(easy = 0, hard = 0, particle = 0)
@RequiresApi(api = Build.VERSION_CODES.N)
public class Ⅱ_缺失的第一个正整数 {

    public static void main(String[] args) {
        int[] data = new int[]{-2, 1,11,12,13};
        LogUtil.print(HardSolution.minNumberDisappeared(data));
    }

    //桶
    private static class EasySolution {
        public static int minNumberDisappeared(int[] nums) {
            // write code here
            HashMap<Integer, Integer> maps = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                maps.put(nums[i], maps.getOrDefault(nums[i], 0) + 1);
            }
            int index = 1;
            while (true) {
                if (!maps.containsKey(index)) {
                    return index;
                } else {
                    index++;
                }
            }
        }

    }

    /**
     * nums的长度为n，在最整齐的情况下nums里的元素应该是[1....n]，那么缺失的第一个正整数就是n+1
     * 如果不是这样，那么缺失的第一个正整数肯定是在[1...n]之中，如何找到这个缺的呢？
     * 将nums中属于[1...n]的数对应的下标，比如说2，就在序号为2，也就是下标为1的地方做一个标记
     * 如果nums中所有位置都有标记，那么结果就是n+1，否则第一个没有标记的序号就是缺失的正整数
     * 标记怎么做？设计为什么？设计成那个数的相反数最好了，这样不管是那个下标是否处理过，只要是我们都面对
     * 绝对值来操作就不会有问题，而若是设计成别的数，比如-1就会顶掉那个下标的数，就会复杂化了。
     */
    private static class HardSolution {


        /**
         * https://leetcode.cn/problems/first-missing-positive/solutions/304743/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
         */
        public static int minNumberDisappeared(int[] nums) {
            if (nums == null || nums.length == 0) return 1;
            int N = nums.length;
            //1.把不在 [1,N] 范围内的数修改成任意一个大于 N 的数（例如 N+1）。这样一来，数组中的所有数就都是正数了
            for (int i = 0; i < N; i++) {
                if (nums[i] <= 0) {
                    nums[i] = N + 1;
                }
            }
            //2.对于遍历到的数 x，如果它在 [1,N] 的范围内，那么就将数组中的第 x−1 个位置（注意：数组下标从 0 开始）打上「标记」。
            for (int i = 0; i < N; i++) {
                int cur = Math.abs(nums[i]); //它可能已经被打了标记，因此原本对应的数为 ∣x∣
                if (cur <= N) {
                    //如果 ∣x∣∈[1,N]，那么我们给数组中的第 ∣x∣−1 个位置的数添加一个负号。注意如果它已经有负号，不需要重复添加；
                    nums[cur - 1] = nums[cur - 1] < 0 ? nums[cur - 1] : -nums[cur - 1];
                }
            }
            //3.遍历找到第一个非负的序号，那就是缺的
            for (int i = 0; i < N; i++) {
                if (nums[i] >= 0) {
                    return i + 1;
                }
            }
            return N + 1;
        }
    }

}
