package com.ng.code.menu.链表;

import com.ng.base.utils.DoubleListNode;
import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:
 * 阿里一面原题
 * 原题描述:
 * 示例:
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_双向链表反转 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        DoubleListNode doubleListNode = DoubleListNode.getNodeList(1, 2, 3, 4, 5);
        LogUtil.print("原数据：" + doubleListNode.toString());
        LogUtil.print(easySolution.reverseDoublyLinkedList(doubleListNode).toString());
    }

    //迭代
    private static class EasySolution {

        public DoubleListNode reverseDoublyLinkedList(DoubleListNode head) {
            DoubleListNode pre = null;
            DoubleListNode cur = head;
            DoubleListNode next;
            while (cur != null) {
                next = cur.next;

                cur.next = pre;
                cur.prev = next;


                pre = cur;
                cur = next;
            }

            return pre;
        }

    }

    private static class HardSolution {


    }

}
