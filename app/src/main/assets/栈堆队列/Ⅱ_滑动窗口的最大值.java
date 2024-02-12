package com.ng.code.menu.栈堆队列;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=295&tqId=23458&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 * 窗口大于数组长度或窗口长度为0的时候，返回空。
 * 数据范围： 1 \le n \le 100001≤n≤10000，0 \le size \le 100000≤size≤10000，数组中每个元素的值满足 |val| \le 10000∣val∣≤10000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * 示例:
 * 输入：
 * [2,3,4,2,6,2,5,1],3
 * 返回值：
 * [4,4,6,6,6,5]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_滑动窗口的最大值 {

    public static void main(String[] args) {
        int[] data = new int[]{
                2, 3, 4, 2, 6, 2, 5, 1
        };
        //LogUtil.pring(EasySolution.maxInWindows(data, 3).toString());
        LogUtil.print(HardSolution.maxInWindows(data, 3).toString());

    }

    /**
     * 第一种相当于在暴力法基础上实现了优化。每次窗口记录一个Max，如果移动窗口，新进入的值大于max，更新max。否则重新遍历窗口寻的下一个MAX。
     */
    private static class EasySolution {
        private static ArrayList<Integer> maxInWindows(int[] num, int size) {
            ArrayList<Integer> list = new ArrayList<>();
            if (num == null || num.length < size || size <= 0) {
                return list;
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {//找到第一个窗口个的最大值
                if (num[i] > max) {
                    max = num[i];
                }
            }
            list.add(max);
            for (int i = size; i < num.length; i++) {
                if (num[i] >= max) { //如果新来的大于max，更新max
                    max = num[i];
                } else { //否则，重新计算max
                    max = num[i];
                    for (int k = i - 1; k > i - size; k--) {
                        if (num[k] > max) {
                            max = num[k];
                        }
                    }
                }
                list.add(max);
            }
            return list;
        }
    }

    /**
     * 利用算双向单调链表（队列）来维护数据。不再重新遍历。
     */
    private static class HardSolution {

        private static ArrayList<Integer> maxInWindows(int[] num, int size) {
            /**
             * Description:利用双向单调队列实现
             * @auther
             */
            ArrayList<Integer> res = new ArrayList<>();
            if (num == null || num.length < size || size <= 0) {
                return res;
            }
            LinkedList<Integer> wQ = new LinkedList<>();
            for (int i = 0; i < num.length; i++) {
                while (!wQ.isEmpty() && num[wQ.peekLast()] <= num[i]) {
                    wQ.pollLast(); //队列里面是由大到小，如果有更大的进来，尾部弹出，直到为空或者里面放的值大于新进入的值
                }
                wQ.addLast(i); //新值入队（存放的是坐标！）
                if (wQ.peekFirst() == i - size) {//去旧值，如果存放的坐标已经不再窗口内，肯定去除啊。
                    wQ.pollFirst();
                }
                if (i >= size - 1) {
                    res.add(num[wQ.peekFirst()]); //依次储存最大值即可
                }
                LogUtil.print("wq:" + wQ.toString());
            }
            return res;
        }
    }

}
