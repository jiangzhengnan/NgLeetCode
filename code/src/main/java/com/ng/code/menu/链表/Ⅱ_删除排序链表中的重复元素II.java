package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * <p>
 * 示例:
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_删除排序链表中的重复元素II {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

        ListNode node = ListNode.getNodeList(1, 1);
        LogUtil.print(hardSolution.deleteDuplicates(node));

    }

    private static class EasySolution {

    }

    private static class HardSolution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode pre = dummy;
            while (pre.next != null && pre.next.next != null) {
                if (pre.next.val == pre.next.next.val) {
                    int x = pre.next.val;
                    while (pre.next != null && pre.next.val == x) {
                        pre.next = pre.next.next;
                    }
                } else {
                    pre = pre.next;
                }
            }

            return dummy.next;
        }
    }

}
