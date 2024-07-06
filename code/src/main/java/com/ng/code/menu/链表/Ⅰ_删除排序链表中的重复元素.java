package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 *
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 示例1
 * 输入：
 * {1,1,2}
 * 返回值：
 * {1,2}
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_删除排序链表中的重复元素 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 1, 2, 3, 3);
        LogUtil.print(EasySolution.deleteDuplicates(data));

    }

    /**
     * 因为是排序的链表
     * 直接比较当前节点和上一个节点是否相等，是的话删除当前节点
     */
    private static class EasySolution {

        public static ListNode deleteDuplicates(ListNode head) {
            ListNode cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                while (next != null && cur.val == next.val) {
                    next = next.next;
                }
                cur.next = next;
                cur = cur.next;
            }
            return head;
        }
    }

    //递归解法,难理解
    private static class HardSolution {

        /**
         * 1 只确定递归函数需要传入什么参数，需要返回什么结果，其他不关心；
         * 2 在递归函数里，只编写单次运行时的逻辑代码，其他不关心；
         * 3 始终相信自己定义的递归函数，一定能返回我想要的结果；
         * 4 在代码的任何地方，只要是需要用到递归函数时，直接调用，返回值就直接拿来主义，
         * 永远不要在脑子里模拟递归调用递归的细节；
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            if (head.val != head.next.val) {
                head.next = deleteDuplicates(head.next);
            } else {
                while (head.next != null && head.val == head.next.val) {
                    head.next = head.next.next;
                }
                head = deleteDuplicates(head.next);
            }
            return head;
        }
    }


}
