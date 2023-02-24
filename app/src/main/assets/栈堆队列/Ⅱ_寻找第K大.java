package com.ng.code.menu.栈堆队列;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.PriorityQueue;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=295&tqId=44581&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第 k 大的数。
 * 给定一个整数数组 a ,同时给定它的大小n和要找的 k ，请返回第 k 大的数(包括重复的元素，不用去重)，保证答案存在。
 * 要求：时间复杂度 O(nlogn)O(nlogn)，空间复杂度 O(1)O(1)
 * 数据范围：0\le n \le 10000≤n≤1000， 1 \le K \le n1≤K≤n，数组中每个元素满足 0 \le val \le 100000000≤val≤10000000
 * 示例:
 * 输入：
 * [1,3,5,2,2],5,3
 * 返回值：
 * 2
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_寻找第K大 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 5, 2, 2};

        LogUtil.print(EasySolution.findKth(data, 5, 3));
        LogUtil.print(HardSolution.findKth(data, 5, 3));

    }

    //堆排序
    private static class EasySolution {
        public static int findKth(int[] a, int n, int K) {
            // 暂存K个较大的值，优先队列默认是自然排序（升序），队头元素（根）是堆内的最小元素，也就是小根堆
            PriorityQueue<Integer> queue = new PriorityQueue<>(K);
            // 遍历每一个元素，调整小根堆
            for (int num : a) {
                // 对于小根堆来说，只要没满就可以加入（不需要比较）；如果满了，才判断是否需要替换第一个元素
                if (queue.size() < K) {
                    queue.add(num);
                } else {
                    // 在小根堆内，存储着K个较大的元素，根是这K个中最小的，如果出现比根还要大的元素，说明可以替换根
                    if (num > queue.peek()) {
                        queue.poll(); // 高个中挑矮个，矮个淘汰
                        queue.add(num);
                    }
                }
            }
            return queue.isEmpty() ? 0 : queue.peek();
        }
    }

    //快速排序的思想--随机选择法
    private static class HardSolution {
        public static int findKth(int[] a, int n, int K) {
            return quickSort(a, 0, a.length - 1, K);
        }

        private static int quickSort(int[] arr, int left, int right, int k) {
            int p = partition(arr, left, right);
            // 改进后，很特殊的是，p是全局下标，只要p对上topK坐标就可以返回
            if (p == arr.length - k) {
                return arr[p];
            } else if (p < arr.length - k) {
                // 如果基准在左边，这在右边找
                return quickSort(arr, p + 1, right, k);
            } else {
                return quickSort(arr, left, p - 1, k);
            }
        }

        private static int partition(int[] arr, int left, int right) {
            // 可优化成随机，或中位数
            int key = arr[left];
            while (left < right) {
                while (left < right && arr[right] >= key) right--;
                arr[left] = arr[right];
                while (left < right && arr[left] <= key) left++;
                arr[right] = arr[left];
            }
            arr[left] = key;
            return left;
        }

    }

}
