package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/02bf49ea45cd486daa031614f9bd6fc3?tpId=295&tqId=1073463&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
 * 注意是节点的编号而非节点的数值。
 * <p>
 * 数据范围：节点数量满足 0 <= n <= 10^50≤n≤10
 * 5
 * ，节点中的值都满足 0 <= val <= 10000≤val≤1000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 */
@Solution(easy = 1, hard = 0)
public class 链表的奇偶重排 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(2, 1, 3, 5, 6, 4, 7);

        LogUtil.pring(SolutionHard.oddEvenList(data));
    }

    private static class SolutionEasy {

        /**
         * 1.取出奇节点数组
         * 2.取出偶节点数组
         * 3.拼起来
         */
        public static ListNode oddEvenList(ListNode head) {
            // write code here
            List<ListNode> jiList = new ArrayList<>();
            List<ListNode> ouList = new ArrayList<>();
            int nowWei = 1;
            while (head != null) {
                if (nowWei % 2 == 0) {
                    ouList.add(head);
                } else {
                    jiList.add(head);
                }
                head = head.next;
                nowWei++;
            }

            ListNode ya = new ListNode(-1);
            head = ya;
            for (ListNode temp : jiList) {
                head.next = new ListNode(temp.val);
                head = head.next;
            }
            for (ListNode temp : ouList) {
                head.next = new ListNode(temp.val);
                head = head.next;
            }

            return ya.next;
        }

    }

    private static class SolutionHard {

        /**
         * 1.odd节点指向奇数位 even节点指向偶数位
         * 2.分别拼接奇数位链表和偶数位链表
         * 3.合并两条链表
         */
        public static ListNode oddEvenList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode evenHead = head.next;
            ListNode odd = head;
            ListNode even = evenHead;
            while (even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;
                even.next = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
            return head;
        }
    }


}
