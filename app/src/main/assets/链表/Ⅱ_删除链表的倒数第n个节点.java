package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * 原题描述:
 */
@Solution(easy = 1, hard = 0)
public class Ⅱ_删除链表的倒数第n个节点 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(new int[]{1, 2});
        LogUtil.pring(HardSolution.removeNthFromEnd(data, 0));
    }

    //双指针
    private static class EasySolution {
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            // write code here
            ListNode slow = head;
            ListNode fast = head;

            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }

            //如果fast为空，表示删除的是头结点
            if (fast == null) {
                return head.next;
            }

            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
            return head;

        }
    }

    //递归
    private static class HardSolution {
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            int pos = length(head, n);
            // 说明删除的是头节点
            if (pos == n) {
                return head.next;
            }
            return head;

        }

        private static int length(ListNode node, int n) {
            if (node == null) {
                return 0;
            }
            int pos = length(node.next, n) + 1;
            if (pos == n + 1) {
                node.next = node.next.next;
            }
            return pos;
        }
    }


}
