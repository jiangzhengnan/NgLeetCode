package com.ng.code.menu.贪心算法;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/next-permutation/
 * 字节一面原题
 * 原题描述:
 * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
 * <p>
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，
 * 那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 * <p>
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例:
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_下一个排列 {

    public static void main(String[] args) {
        int[] data = new int[]{4, 5, 2, 6, 3, 1};
        EasySolution easySolution = new EasySolution();
        easySolution.nextPermutation(data);
        LogUtil.print(data);
    }

    private static class EasySolution {
        /**
         * 算法推导
         * 如何得到这样的排列顺序？这是本文的重点。我们可以这样来分析：
         * 我们希望下一个数 比当前数大，这样才满足 “下一个排列” 的定义。因此只需要 将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。比如 123456，将 5 和 6
         * 交换就能得到一个更大的数 123465。
         * 我们还希望下一个数 增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
         * 在 尽可能靠右的低位 进行交换，需要 从后向前 查找
         * 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
         * 将「大数」换到前面后，需要将「大数」后面的所有数 重置为升序，升序排列就是最小的排列。以 123465 为例：首先按照上一步，交换 5 和 4，得到
         * 123564；然后需要将 5 之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
         * 以上就是求 “下一个排列” 的分析过程。
         * <p>
         * 算法过程
         * 标准的 “下一个排列” 算法可以描述为：
         * 从后向前 查找第一个 相邻升序 的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
         * 在 [j,end) 从后向前 查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
         * 将 A[i] 与 A[k] 交换
         * 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
         * 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
         */
        public void nextPermutation(int[] nums) {
            if (nums.length <= 1) {
                return;
            }

            int i = nums.length - 2;
            int j = nums.length - 1;
            int k = nums.length - 1;

            // find: A[i]<A[j]
            while (i >= 0 && nums[i] >= nums[j]) {
                i--;
                j--;
            }

            if (i >= 0) { // not the last permutation
                // find: A[i]<A[k]
                while (nums[i] >= nums[k]) {
                    k--;
                }
                // swap A[i], A[k]
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }

            // reverse A[j:end]
            i = j;
            j = nums.length - 1;
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
    }


    private static class HardSolution {

        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i] >= nums[i + 1]) {
                i--;
                //1.找到满足 A[i] < A[i + 1]。i为从右往左数，相对的较小数，此时 [i + 1,end) 必然是降序
                // 比如在4,5,2,6,3,1中，找到的i就是2
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[i] >= nums[j]) {
                    j--;
                }
                // 从右往左，找到相对i来说的较大数。因为需要尽可能小，且i～j是降序，所以需要从右往左。
                //比如在4,5,2,6,3,1中，这里找到的j是3
                swap(nums, i, j);

                //交换之后，为4,5,3,6,2,1
            }

            reverse(nums, i + 1);
            //此时，再讲i + 1～end反转为升序，即可得到结果 4 5 3 1 2 6  1,3
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void reverse(int[] nums, int start) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }

}
