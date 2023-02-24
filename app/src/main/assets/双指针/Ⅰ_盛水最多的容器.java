package com.ng.code.menu.双指针;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给定一个数组height，长度为n，每个数代表坐标轴中的一个点的高度，height[i]是在第i点的高度，请问，从中选2个高度与x轴组成的容器最多能容纳多少水
 * 1.你不能倾斜容器
 * 2.当n小于2时，视为不能形成容器，请返回0
 * 3.数据保证能容纳最多的水不会超过整形范围，即不会超过231-1
 *
 * 示例:
 * 输入：
 * [1,7,3,2,4,5,8,2,7]
 * 返回值：
 * 49
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅰ_盛水最多的容器 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 7, 3, 2, 4, 5, 8, 2, 7};
        LogUtil.pring(EasySolution.maxArea(data));
    }

    /**
     * 若向内移动短板，水槽的短板可能变大，因此面积可能增加
     * 若向内移动长板，水槽的短板不会变大，面积只可能减小
     * 因此，循环每轮将短板向内移动一个，并更新面积最大值，直到两个指针相遇，即可得到最大值。
     */
    private static class EasySolution {

        public static int maxArea(int[] height) {
            if (height == null || height.length < 2) {
                return 0;
            }
            int start = 0, end = height.length - 1;
            int max = 0;
            while (end > start) {
                int temp = (end - start) * Math.min(height[start], height[end]);
                max = Math.max(temp, max);
                if (height[start] > height[end]) {
                    end--;
                } else {
                    start++;
                }
            }
            return max;
        }

    }

    /**
     * 在双指针方法中，虽然时间复杂度为O(N)O(N)O(N)，但是每次移动都需要一次比较，我们可以进行优化，
     * 减少比较的次数，这里我们考虑到，如果向内移动时，如果短边的长度变短，那么移动后的面积一定小于移动前的面积，就可以不进行比较，知道移动后的面积大于移动前的面积。
     */
    private static class HardSolution {

        public int maxArea(int[] height) {
            // write code here
            int l = 0, r = height.length - 1;
            int ans = 0;
            while (l < r) {
                int area = Math.min(height[l], height[r]) * (r - l);
                int h = Math.min(height[l], height[r]);
                ans = Math.max(area, ans);
                while (height[l] <= h && l < r) {  //当新短边大于原短边时停止
                    l++;
                }
                while (height[r] <= h && l < r) {  //当新短边大于原短边时停止
                    r--;
                }
            }
            return ans;
        }


    }

}
