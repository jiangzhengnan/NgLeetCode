package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * <p>
 * 描述:
 * 将给出的链表中的节点每 k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是 k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 * <p>
 * 数据范围：  2000 0≤n≤2000 ，  20001≤k≤2000 ，链表中每个元素都满足 0   10000≤val≤1000
 * 要求空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 例如：
 * 给定的链表是  1→2→3→4→5
 * 对于 k = 2k=2 , 你应该返回 2→1→4→3→5
 * 对于 k = 3k=3 , 你应该返回 3→2→1→4→5
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅲ_K个一组翻转链表 {

    public static void main(String[] args) {
        LogUtil.print(reverseKGroup(ListNode.getNodeList(1, 2, 3, 4, 5), 2));
    }

    /*
     pre ..k.. tailNode
    pre  pre.next(start)   tail  tail.next(next)
     0    1                  2      3            4   5   6
      */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode resultHead = new ListNode(-1);
        resultHead.next = head;

        ListNode pre = resultHead;
        ListNode start = pre.next;
        ListNode next;
        ListNode tail;
        while ((tail = getTailNode(start, k)) != null) {

            next = tail.next;
            tail.next = null;

            revers(start);
            pre.next = tail;
            start.next = next;

            pre = start;
            start = pre.next;
        }


        return resultHead.next;
    }

    private static ListNode revers(ListNode node) {
        ListNode pre = null;
        ListNode now = node;
        ListNode next = null;
        while (now != null) {
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
        }
        return pre;
    }

    private static ListNode getTailNode(ListNode node, int k) {
        ListNode temp = node;
        while (temp != null && k > 1) {
            k--;
            temp = temp.next;
        }
        return temp;
    }


}
