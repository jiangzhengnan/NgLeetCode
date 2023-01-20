package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * 原题描述:
 *
 * 示例:
 */
@Solution(easy = 0, hard = 1, partice = 2)
public class Ⅰ_链表反转 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(36,4);
        LogUtil.pring(data);

        //LogUtil.pring(EasySolution.reverseList(data));
        LogUtil.pring(HardSolution.reverseList(data));
    }

    //递归
    private static class EasySolution {

        public static ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode next = head.next;
            ListNode newHead = reverseList(next);
            next.next = head;
            head.next = null;
            return newHead;
        }

    }

    //迭代
    private static class HardSolution {

        public static ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode now = head;
            while (now != null) {
                ListNode next = now.next;

                now.next = pre;
                pre = now;

                now = next;
            }

            return pre;
        }


    }

}
