package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/3a188e9c06ce4844b031713b82784a2a?tab=note
 * 原题描述:
 * 描述
 * 给定一个奇数位升序，偶数位降序的链表，返回对其排序后的链表。
 * 题面解释：例如链表 1->3->2->2->3->1 是奇数位升序偶数位降序的链表，而 1->3->2->2->3->2 则不符合题目要求。
 * 数据范围：链表中元素个数满足
 * 示例1
 * 输入：
 * {1,3,2,2,3,1}
 * 复制
 * 返回值：
 * {1,1,2,2,3,3}
 * 复制
 * 示例2
 * 输入：
 * {1,2,2}
 * 复制
 * 返回值：
 * {1,2,2}
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅱ_排序奇升偶降链表 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        ListNode node = ListNode.getNodeList(1, 3, 2, 2, 3, 1);
        LogUtil.print(easySolution.sortLinkedList(node));
    }

    private static class EasySolution {

        public ListNode sortLinkedList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode oddHead = head;
            ListNode evenHead = head.next;

            ListNode odd = oddHead;
            ListNode even = evenHead;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = null;
            return merge(oddHead, revers(evenHead));
        }

        public ListNode merge(ListNode n1, ListNode n2) {
            if (n1 == null) {
                return n2;
            }
            if (n2 == null) {
                return n1;
            }
            if (n1.val < n2.val) {
                n1.next = merge(n1.next, n2);
                return n1;
            } else {
                n2.next = merge(n2.next, n1);
                return n2;
            }

        }

        public ListNode revers(ListNode node) {
            ListNode pre = null;
            ListNode cur = node;
            ListNode next;
            while (cur != null) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

    }

    private static class HardSolution {

    }

}
