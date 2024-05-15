package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/sort-list/description/?envType=study-plan-v2&envId=top-100-liked
 * <p>
 * 原题描述:
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅱ_排序链表 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 3, 2, 4, 5);
        LogUtil.print(new HardSolution().sortList(data));
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
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode second = slow.next;
            slow.next = null;
            //递归左右两边
            ListNode left = sortList(head);
            ListNode right = sortList(second);
            //合并
            return merge(left, right);
        }

        private static ListNode merge(final ListNode left, final ListNode right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            if (left.val > right.val) {
                right.next = merge(right.next, left);
                return right;
            } else {
                left.next = merge(left.next, right);
                return left;
            }
        }
    }


}

