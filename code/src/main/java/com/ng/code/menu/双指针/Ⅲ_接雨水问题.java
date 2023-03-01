package com.ng.code.menu.双指针;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，
 * 计算按此排列的柱子，下雨之后能接多少雨水。(数组以外的区域高度视为0)
 *
 * 示例:
 * 输入：
 * [3,1,2,5,2,4]
 * 返回值：
 * 5
 * 说明：
 * 数组 [3,1,2,5,2,4] 表示柱子高度图，在这种情况下，可以接 5个单位的雨水，蓝色的为雨水 ，如题面图。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅲ_接雨水问题 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.maxWater(new int[]{4, 5, 1, 3, 2}));
    }

    /**
     * 双指针
     * 1.最开始的时候如果左边柱子从左往右是递增的，那么这些柱子是不能盛水的，
     * 同理最开始的时候如果右边的柱子从右往左是递增的，也是不能盛水的。所以上面图中right指向的是右边第2根柱子。
     * 确定左右两边柱子的的代码如下
     * int left = 0, right = height.length - 1;
     * while (left < right && height[left] <= height[left + 1])
     * left++;
     * while (left < right && height[right] <= height[right - 1])
     * right--;
     * 通过上面的计算，确定left和right的值之后，在left和right之间相当于构成了一个桶，桶的高度是最矮的那根柱子。
     * 然后我们从两边往中间逐个查找，如果查找的柱子高度小于桶的高度，那么盛水量就是桶的高度减去我们查找的柱子高度，
     * 如果查找的柱子大于桶的高度，我们要更新桶的高度。我们来看下最终代码
     */
    private static class EasySolution {
        public static long maxWater(int[] arr) {
            if (arr.length <= 2)
                return 0;
            long water = 0;
            int left = 0, right = arr.length - 1;
            //最开始的时候确定left和right的边界，这里的left和right是
            //柱子的下标，不是柱子的高度
            while (left < right && arr[left] <= arr[left + 1])
                left++;
            while (left < right && arr[right] <= arr[right - 1])
                right--;

            while (left < right) {
                int leftValue = arr[left];
                int rightValue = arr[right];
                //在left和right两根柱子之间计算盛水量
                if (leftValue <= rightValue) {
                    //如果左边柱子高度小于等于右边柱子的高度，根据木桶原理，
                    // 桶的高度就是左边柱子的高度
                    while (left < right && leftValue >= arr[++left]) {
                        water += leftValue - arr[left];
                    }
                } else {
                    //如果左边柱子高度大于右边柱子的高度，根据木桶原理，
                    // 桶的高度就是右边柱子的高度
                    while (left < right && arr[--right] <= rightValue) {
                        water += rightValue - arr[right];
                    }
                }
            }
            return water;
        }

    }

    //合并简化代码
    private static class HardSolution {

        public long maxWater(int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            long water = 0;
            int leftmax = 0;
            int rightmax = 0;
            while (left < right) {
                //确定左边的最高柱子
                leftmax = Math.max(leftmax, arr[left]);
                //确定左边的最高柱子
                rightmax = Math.max(rightmax, arr[right]);
                //那么桶的高度就是leftmax和rightmax中最小的那个
                if (leftmax < rightmax) {
                    //桶的高度是leftmax
                    water += (leftmax - arr[left++]);
                } else {
                    //桶的高度是rightmax
                    water += (rightmax - arr[right--]);
                }
            }
            return water;
        }

    }

}
