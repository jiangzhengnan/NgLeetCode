package com.ng.code.menu.双指针;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link: https://leetcode.cn/problems/merge-two-sorted-lists/description/?envType=study-plan-v2
 * &envId=top-100-liked
 * 原题描述:
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_合并两个有序链表 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();


        ListNode list1 = ListNode.getNodeList(1, 2, 4);
        ListNode list2 = ListNode.getNodeList(1, 3, 4);

        LogUtil.print(EasySolution.mergeTwoLists(list1, list2));
    }

    private static class EasySolution {

        /**
         * 迭代方式，双指针做
         */
        public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 虚拟头结点
            ListNode dummy = new ListNode(-1), p = dummy;
            ListNode p1 = l1, p2 = l2;

            while (p1 != null && p2 != null) {
                // 比较 p1 和 p2 两个指针
                // 将值较小的的节点接到 p 指针
                if (p1.val > p2.val) {
                    p.next = p2;
                    p2 = p2.next;
                } else {
                    p.next = p1;
                    p1 = p1.next;
                }
                // p 指针不断前进
                p = p.next;
            }

            if (p1 != null) {
                p.next = p1;
            }

            if (p2 != null) {
                p.next = p2;
            }

            return dummy.next;
        }

    }

    private static class HardSolution {

        /**
         * 递归方式
         */
        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            if (list1.val > list2.val) {
                list2.next = mergeTwoLists(list2.next, list1);
                return list2;
            } else {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }
        }

    }

}
