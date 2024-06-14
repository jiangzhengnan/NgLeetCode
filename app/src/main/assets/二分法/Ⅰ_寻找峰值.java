package com.ng.code.menu.二分法;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/find-peak-element/?favorite=2ckc81c
 * 原题描述:
 * 描述
 * 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
 * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
 * 2.假设 nums[-1] = nums[n] = -\infty−∞
 * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
 * <p>
 * 输入：
 * [2,4,1,2,7,8,4]
 * 返回值：
 * 1
 * 说明：
 * 4和8都是峰值元素，返回4的索引1或者8的索引5都可以
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_寻找峰值 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        LogUtil.print(EasySolution.findPeakElement(data));

    }

    //暴力法求山峰,时间复杂度On
    //或者直接求最大值。。也是符合题意的
    private static class EasySolution {
        public static int findPeakElement(int[] nums) {
            // write code here
            for (int i = 0; i <= nums.length - 1; i++) {
                if (i == 0) {
                    if (i + 1 < nums.length - 1 && nums[i] > nums[i + 1]) {
                        return i;
                    } else {
                        continue;
                    }
                }
                if (i == nums.length - 1) {
                    if (nums[i] > nums[i - 1]) {
                        return i;
                    } else {
                        continue;
                    }
                }
                if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                    return i;
                }
            }

            return 0;
        }

    }

    /**
     * 二分法
     * 时间复杂度o(logn),空间复杂度o(1)
     * nums[mid] < nums[mid + 1]说明在“上坡”，则可以使left = mid + 1（因为mid肯定不是峰值），向“峰”处压缩
     * nums[mid] > nums[mid + 1]说明在“下坡”，则应该使right = mid（mid可能是峰值），往“峰”处压缩
     *
     * 中点所在地方，可能是某座山的山峰，山的下坡处，山的上坡处，如果是山峰，最后会二分终止也会找到，
     * 关键是我们的二分方向，并不知道山峰在我们左边还是右边，送你两个字你就明白了，爬山（没错，就是带你去爬山），
     * 如果你往下坡方向走，也许可能遇到新的山峰，但是也许是一个一直下降的坡，
     * 最后到边界。但是如果你往上坡方向走，就算最后一直上的边界，由于最边界是负无穷，所以就一定能找到山峰，
     * 总的一句话，往递增的方向上，二分，一定能找到，往递减的方向只是可能找到，也许没有。
     *
     * 总结下来就是：因为是寻找山峰，所以要往上坡方向找。
     */
    private static class HardSolution {
        public static int findPeakElement(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] > nums[mid + 1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
        }
    }

}
