package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c?tpId=295&tqId=654&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 描述
 * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)。
 * 例如：
 * 给出的链表为 1\to 2 \to 3 \to 4 \to 5 \to NULL1→2→3→4→5→NULL, m=2,n=4m=2,n=4,
 * 返回 1\to 4\to 3\to 2\to 5\to NULL1→4→3→2→5→NULL.
 * 数据范围： 链表长度 0 < size \le 10000<size≤1000，0 < m \le n \le size0<m≤n≤size，链表中每个节点的值满足 |val| \le 1000∣val∣≤1000
 * 要求：时间复杂度 O(n)O(n) ，空间复杂度 O(n)O(n)
 * 进阶：时间复杂度 O(n)O(n)，空间复杂度 O(1)O(1)
 */
@Solution(easy = 0, hard = 1, particle = 1)
public class Ⅱ_链表内指定区间反转 {

    public static void main(String[] args) {
        LogUtil.print(
                HardSolution.reverseBetween(ListNode.getNodeList(new int[]{1, 2, 3, 4, 5, 6}), 2, 4));
    }

    //一次遍历法
    // 固定子区间外的节点，在反转区间内，每遍历到一个节点，让这个新节点来到反转部分的起始位置。
    // 不断的把2后面的挪到pre后面来
    private static class HardSolution {
        public static ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            ListNode pre = dummyNode;

            LogUtil.print(dummyNode);


            //确定头节点
            for (int i = 0; i < m - 1; i++) {
                pre = pre.next;
            }
            ListNode cur = pre.next;
            ListNode Cur_next = null;
            for (int i = 0; i < n - m; i++) {
                LogUtil.print("cur:" + cur.val);
                LogUtil.print("cur_next:" + (Cur_next == null ? "null" : Cur_next.val + ""));

                Cur_next = cur.next;
                cur.next = Cur_next.next;
                Cur_next.next = pre.next;
                pre.next = Cur_next;


            }
            return dummyNode.next;
        }
    }

    //双指针解法
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // write code here
        ListNode
            left = null,
            right = head,
            start = head,
            end = null;
        while (--m > 0) {
            left = start;
            start = start.next;
        }
        while (n-- > 0) {
            end = right;
            right = right.next;
        }
        if (right != null) {
            end.next = null;
        }
        ListNode newHead = revers(start);
        if (left != null) {
            left.next = newHead;
        } else {
            head = newHead;
        }
        if (right != null) {
            start.next = right;
        }

        return head;
    }

    private ListNode revers(ListNode node) {
        ListNode pre = null;
        ListNode now = node;
        ListNode next = null;
        while (now != null) {
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
    }


}
