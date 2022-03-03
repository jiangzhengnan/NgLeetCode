package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/c56f6c70fb3f4849bc56e33ff2a50b6b?tpId=295&tqId=1008772&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 数据范围：0 \le n,m \le 10000000≤n,m≤1000000，链表任意值 0 \le val \le 90≤val≤9
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * <p>
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */
@Solution(easy = 1, hard = 1)
public class 链表相加二 {

    public static void main(String[] args) {
        ListNode node1 = ListNode.getNodeList(5,9,7,5,7,1,2,6,4,2,7,8,9,6,1,6,6,1,1,4,2,9,5,5,0,4,6,3,0,4,3,5,6,7,0,5,5,4,4,0);
        ListNode node2 = ListNode.getNodeList(1,3,2,5,0,6,0,2,1,4,3,9,3,0,9,9,0,3,1,6,5,7,8,6,2,3,8,5,0,9,7,9,4,5,9,9,4,9,3,6);
        LogUtil.pring(SolutionEasy.addInList(node1, node2));
    }

    /**
     * 栈相加法，几乎双百
     */
    private static class SolutionEasy {
        public static ListNode addInList(ListNode head1, ListNode head2) {
            // write code here
            Stack<Integer> stack1 = new Stack<>();
            while (head1 != null) {
                stack1.push(head1.val);
                head1 = head1.next;
            }
            Stack<Integer> stack2 = new Stack<>();
            while (head2 != null) {
                stack2.push(head2.val);
                head2 = head2.next;
            }

            ListNode newNode = new ListNode(-1);
            ListNode nowNode = newNode;
            int yu = 0;
            while (stack1.size() > 0 || stack2.size() > 0) {
                int temp = yu;
                if (stack1.size() > 0) {
                    temp += stack1.pop();
                }
                if (stack2.size() > 0) {
                    temp += stack2.pop();
                }
                if (temp >= 10) {
                    yu = 1;
                    temp = temp % 10;
                } else {
                    yu = 0;
                }
                nowNode.next = new ListNode(temp);
                nowNode = nowNode.next;
            }
            if (yu == 1) {
                nowNode.next = new ListNode(1);
            }
            nowNode = reverse(newNode.next);
            return nowNode;

        }

        private static ListNode reverse(ListNode now) {
            ListNode pre = null;
            while (now != null) {
                ListNode next = now.next;
                now.next = pre;
                pre = now;
                now = next;
            }
            return pre;
        }
    }

    private static class SolutionHard {
    }


}
