package com.ng.code.menu.链表;

import com.ng.base.ListNode;
import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/f23604257af94d939848729b1a5cda08?tpId=295&tqId=1008897&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅲ_单链表的排序 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 3, 2, 4, 5);
        LogUtil.pring(HardSolution.sortInList(data));
    }

    //辅助数组排序
    private static class EasySolution {
    }

    /**
     * 归并递归排序
     * 1.将链表断开，直到只有一个节点
     * 2.合并，将两个链表合并，转化为排序链表
     */
    private static class HardSolution {
        public static ListNode sortInList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            //寻找中点, 这里取head.next很重要!!
            ListNode fast = head.next;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode temp = slow.next;
            slow.next = null;
            //递归左右两边
            ListNode left = sortInList(head);
            ListNode right = sortInList(temp);

            //创建新的链表
            ListNode h = new ListNode(0);
            ListNode res = h;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            h.next = left != null ? left : right;
            return res.next;
        }
    }


}
