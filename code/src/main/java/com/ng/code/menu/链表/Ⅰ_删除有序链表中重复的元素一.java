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
public class Ⅰ_删除有序链表中重复的元素一 {

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
            ListNode pre = null;
            ListNode now = head;
            while (now != null) {
                if (pre != null && pre.val == now.val) {
                    pre.next = now.next;
                } else {
                    pre = now;
                }
                now = now.next;
            }
            return head;
        }
    }

    //与上面相同
    private static class HardSolution {
    }


}
