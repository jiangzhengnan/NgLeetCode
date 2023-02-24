package com.ng.code.menu.链表;

import com.ng.base.ListNode;
import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/3fed228444e740c8be66232ce8b87c2f?tpId=295&tqId=1008769&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 1 2 2 1
 */
@Solution(easy = 0, hard = 0, partice = 1)
public class Ⅱ_判断一个链表是否为回文结构 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 2, 3,4, 3, 2, 1);
        LogUtil.pring(HardSolution.isPail(data));

    }

    //使用栈解决
    private static class EasySolution {

        public static boolean isPail(ListNode head) {
            // write code here
            Stack<Integer> cache = new Stack<>();
            ListNode temp = head;
            while (temp != null) {
                cache.push(temp.val);
                temp = temp.next;
            }

            while (head != null) {
                if (head.val != cache.pop()) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }
    }

    /**
     * 1.通过快慢指针找到中点
     * 2.反转后半部分
     * 3.比较两段是否相等
     */
    private static class HardSolution {

        public static boolean isPail(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next !=null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            // 1 2 3 4
            // 1 2 3 4 5
            //如果fast不为空，说明链表的长度是奇数个
            if (fast != null) {
                slow = slow.next;
            }

            slow = reverse(slow);
            fast = head;

            while (slow != null) {
                if (fast.val != slow.val) {
                    return false;
                }
                fast = fast.next;
                slow = slow.next;
            }
            return true;
        }

        private static ListNode reverse(ListNode node) {
            ListNode pre = null;
            ListNode now = node;
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
