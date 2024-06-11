package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/description/?envType=study-plan
 * -v2&envId=top-100-liked
 * <p>
 * 原题描述:
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅱ_相交链表 {

    public static void main(String[] args) {
        ListNode phead1 = ListNode.getNodeList(1, 2, 3);
        ListNode phead2 = ListNode.getNodeList(4, 5);
        ListNode phead3 = ListNode.getNodeList(6, 7);
//        ListNode.mergeNodeList(phead1, phead3);
//        ListNode.mergeNodeList(phead2, phead3);
        LogUtil.print(EasySolution.getIntersectionNode(phead1, phead2));
    }

    /**
     * 双指针
     * 因为 ta + tb = tb + ta
     */
    private static class EasySolution {
        public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode a1 = headA;
            ListNode a2 = headB;
            while (a1 != a2) {
                a1 = a1 == null? headB : a1.next;
                a2 = a2 == null? headA : a2.next;
            }
            return a1;
        }
    }

    private static class HardSolution {
    }

}
