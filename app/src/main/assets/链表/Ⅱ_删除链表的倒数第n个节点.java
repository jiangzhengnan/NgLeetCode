package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?favorite=2cktkvj
 * <p>
 * 日期:
 * 原题链接:
 * 原题描述:
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_删除链表的倒数第n个节点 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 2, 3, 4, 5);
        LogUtil.print(EasySolution.removeNthFromEnd(data, 2));
        LogUtil.print(HardSolution.removeNthFromEnd(data, 2));
    }

    //双指针
    private static class EasySolution {
        public static ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return null;
            }
            ListNode slow = head;
            ListNode fast = head;

            while (n-- > 0) {
                if (fast == null) {
                    return null;
                }
                fast = fast.next;
            }
            // 说明fast递归到尾部，k=size，要删除的是头节点
            if (fast == null) {
                return head.next;
            }

            ListNode pre = slow;
            while (fast != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next;
            }
            pre.next = pre.next.next;

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
            if (pos == n + 1) { //这里+1是为了找到需要删除的节点左边一位节点
                node.next = node.next.next;
            }
            return pos;
        }
    }


}
