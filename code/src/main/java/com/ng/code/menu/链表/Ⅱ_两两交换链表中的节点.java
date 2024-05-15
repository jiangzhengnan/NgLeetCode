package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/swap-nodes-in-pairs/description/?envType=study-plan-v2&envId
 * =top-100
 * 原题描述:
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 * 示例:
 */
@Solution(easy = 0, hard = 0, particle = 1)
public class Ⅱ_两两交换链表中的节点 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {

        /**
         * 递归
         */
        public ListNode swapPairs(ListNode first) {
            //第一个
            if (first == null || first.next == null) {
                return first;
            }
            //第二个 
            ListNode second = first.next;
            //下一个循环
            ListNode nextLoop = swapPairs(second.next);

            //交换
            second.next = first;
            first.next = nextLoop;
            return second;
        }
    }

    private static class HardSolution {

        /**
         * 迭代
         */
        public ListNode swapPairs(ListNode head) {
            ListNode dummyHead = new ListNode(0);
            dummyHead.next = head;
            ListNode temp = dummyHead;
            while (temp.next != null && temp.next.next != null) {
                ListNode node1 = temp.next;
                ListNode node2 = temp.next.next;
                temp.next = node2;
                node1.next = node2.next;
                node2.next = node1;
                temp = node1;
            }
            return dummyHead.next;
        }
    }

}
