package com.ng.code.menu.贪心算法;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link: https://leetcode.cn/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top
 * -interview-150
 * 原题描述:
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_跳跃游戏Ⅱ {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        LogUtil.print(easySolution.jump(new int[]{2, 3, 0, 1, 4}));
    }

    private static class EasySolution {

        /**
         * 反向查找出发位置
         * 我们的目标是到达数组的最后一个位置，
         * 因此我们可以考虑最后一步跳跃前所在的位置，
         * 该位置通过跳跃能够到达最后一个位置。
         * 如果有多个位置通过跳跃都能够到达最后一个位置，
         * 那么我们应该如何进行选择呢？直观上来看，我们可以
         * 「贪心」地选择距离最后一个位置最远的那个位置，
         * 也就是对应下标最小的那个位置。
         * 因此，我们可以从左到右遍历数组，
         * 选择第一个满足要求的位置。
         * 找到最后一步跳跃前所在的位置之后，我们继续贪心地寻找倒数第二步跳跃前所在的位置，
         * 以此类推，直到找到数组的开始位置。
         */
        public int jump(int[] nums) {
            int position = nums.length - 1;
            int steps = 0;
            while (position > 0) {
                for (int i = 0; i < position; i++) {
                    if (i + nums[i] >= position) {
                        position = i;
                        steps++;
                        break;
                    }
                }
            }
            return steps;
        }
    }

    private static class HardSolution {

        /**
         * 正向查找可到达的最大位置
         * 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，
         * 我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。
         * 如果访问最后一个元素，在边界正好为最后一个位置的情况下，
         * 我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。
         */
        public int jump(int[] nums) {
            //2, 3, 0, 1, 4 -> 2
            int length = nums.length;
            int end = 0;
            int maxPosition = 0;
            int steps = 0;
            for (int i = 0; i < length - 1; i++) {
                maxPosition = Math.max(maxPosition, i + nums[i]);
                if (i == end) {
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }
    }

}
