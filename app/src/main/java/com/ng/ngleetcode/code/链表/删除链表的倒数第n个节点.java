package com.ng.ngleetcode.code.链表;

import com.ng.ngleetcode.util.ListNode;
import com.ng.ngleetcode.util.LogUtil;
import com.ng.ngleetcode.util.Solution;

/**
 * 日期:
 * 原题链接:
 * 原题描述:
 */
@Solution(easy = 1, hard = 0)
public class 删除链表的倒数第n个节点 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(new int[]{1, 2});
        LogUtil.pring(SolutionEasy.removeNthFromEnd(data, 2));
    }

    //双指针
    private static class SolutionEasy {
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
    private static class SolutionHard {
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
