package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/886370fe658f41b498d40fb34ae76ff9?tpId=295&tqId=1377477&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 输入：
 * {1,2,3,4,5},2
 * 返回值：
 * {4,5}
 * 说明：
 * 返回倒数第2个节点4，系统会打印后面所有的节点来比较。
 *
 * 双指针
 * 递归
 */
@Solution(easy = 0, hard = 1, partice = 2)
public class Ⅰ_链表中倒数最后k个结点 {

    public static void main(String[] args) {
        LogUtil.pring(HardSolution.FindKthToTail(ListNode.getNodeList(new int[]{1, 2, 3, 4, 5}), 2));
    }

    //双指针
    private static class EasySolution {
        public static ListNode FindKthToTail(ListNode pHead, int k) {
            if (pHead == null) {
                return pHead;
            }
            ListNode first = pHead;
            ListNode second = pHead;
            while (k-- > 0) {
                if (first == null) {
                    return null;
                }
                first = first.next;
            }
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            return second;
        }
    }

    //递归
    private static class HardSolution {

        static int size;

        public static ListNode FindKthToTail(ListNode pHead, int k) {
            if (pHead == null) {
                return pHead;
            }
            ListNode head = FindKthToTail(pHead.next, k);
            size++;
            if (size == k) {
                return pHead;
            }
            return head;
        }
    }


}
