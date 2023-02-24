package com.ng.code.menu.双指针;

import com.ng.base.ListNode;
import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
 * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 *
 * 示例:
 * 输入：
 * [2,3,4,5]
 * 返回值：
 * 4
 * 说明：
 * [2,3,4,5]是最长子数组
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_最长无重复子数组 {

    public static void main(String[] args) {
        int[] data = new int[]{1,2,1,4,1,5,6,7,8,1};
        LogUtil.pring(EasySolution.maxLength(data));

        LogUtil.pring(HardSolution.maxLength(data));
    }

    private static class EasySolution {
        public static int maxLength(int[] arr) {
            // result为当前最长子字符串长度
            // start为当前最长子字符串的起始位置
            int result = 0, start = 0;
            // 如果arr中的元素出现了重复，那么我们就要从这个元素的下一个元素的位置开始计算最大子字符串的字符的个数
            // 于是end数组用于记录arr中的元素如果重复了，那么应该从什么地方开始重新计算
            // end一开始初始化为0，如果获取的值是0，表示该数字未出现过
            // end数组中数字对应的下标的数据如果不为0
            // 这里有个限制条件，arr中的每个元素都是非负数，这样end就不会有下标有负数的情况出现
            int[] end = new int[40000];
            for (int i = 0; i < arr.length; i++) {
                int num = arr[i];
                // 当start比end[num]小时，说明num在之前已经出现过了
                // 于是start变为该数出现的下一个位置重新开始计算最大子字符串
                if (start < end[num]) {
                    start = end[num];
                }
                // 将当前已经保存的最长子字符串的长度与目前正在计算的子字符串的长度进行比较
                if (result < i - start + 1) {
                    result = i - start + 1;
                }
                // 记录arr中该元素最近一次出现时应该从什么地方开始重新计算子字符串
                end[num] = i + 1;
            }
            return result;
        }


    }

    //队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，这样我们就可以保证队列中永远都没有重复的元素
    private static class HardSolution {

        public static int maxLength(int[] arr) {
            //用链表实现队列，队列是先进先出的
            Queue<Integer> queue = new LinkedList<>();
            int res = 0;
            for (int c : arr) {
                while (queue.contains(c)) {
                    //如果有重复的，队头出队
                    queue.poll();
                }
                //添加到队尾
                queue.add(c);
                res = Math.max(res, queue.size());
            }
            return res;
        }
    }

}
