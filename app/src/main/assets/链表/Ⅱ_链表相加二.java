package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
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
 *
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅱ_链表相加二 {

    public static void main(String[] args) {
        ListNode node1 = ListNode.getNodeList(5,9,7,5,7,1,2,6,4,2,7,8,9,6,1,6,6,1,1,4,2,9,5,5,0,4,6,3,0,4,3,5,6,7,0,5,5,4,4,0);
        ListNode node2 = ListNode.getNodeList(1,3,2,5,0,6,0,2,1,4,3,9,3,0,9,9,0,3,1,6,5,7,8,6,2,3,8,5,0,9,7,9,4,5,9,9,4,9,3,6);
        LogUtil.print(EasySolution.addInList(node1, node2));
    }

    /**
     * 栈相加法，几乎双百
     */
    private static class EasySolution {
        public static ListNode addInList(ListNode head1, ListNode head2) {
            // 栈相加法
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
            int add = 0;
            ListNode result = new ListNode(-1);
            ListNode last = null;
            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                int num1 = stack1.isEmpty() ? 0 : stack1.pop();
                int num2 = stack2.isEmpty() ? 0 : stack2.pop();
                int temp = num1 + num2 + add;
                if (temp >= 10) {
                    add = 1;
                    temp %= 10;
                } else {
                    add = 0;
                }
                ListNode tempNode = new ListNode(temp);
                result.next = tempNode;
                tempNode.next = last;
                last = tempNode;
            }
            if (add > 0) {
                ListNode tempNode = new ListNode(1);
                result.next = tempNode;
                tempNode.next = last;
            }
            return result.next;
        }
    }

    private static class HardSolution {
    }


}
