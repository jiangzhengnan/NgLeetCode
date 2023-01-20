package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/c087914fae584da886a0091e877f2c79?tpId=295&tqId=664&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
 * 例如：
 * 给出的链表为1→1→2  ,返回 1→2.
 * 给出的链表为 1→1→2→3→3,返回 1→2→3.
 *
 * 数据范围：链表长度满足 0 <= n <= 1000≤n≤100，链表中任意节点的值满足 |val| <= 100∣val∣≤100
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 示例1
 * 输入：
 * {1,1,2}
 * 返回值：
 * {1,2}
 */
@Solution(easy = 0, hard = 1, partice = 3)
public class Ⅰ_删除有序链表中重复的元素一 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 1, 2, 3, 3);
        LogUtil.pring(EasySolution.deleteDuplicates(data));

    }

    /**
     * 因为是排序的链表
     * 直接比较当前节点和上一个节点是否相等，是的话删除当前节点
     */
    private static class EasySolution {

        public static ListNode deleteDuplicates(ListNode head) {
            ListNode pre = null;
            ListNode now = head;
            ListNode next;
            while (now != null) {
                next = now.next;
                if (pre != null && pre.val == now.val) {
                    pre.next = next;
                    now = next;
                } else {
                    pre = now;
                    now = now.next;
                }
            }
            return head;
        }
    }

    //与上面相同
    private static class HardSolution {
    }


}
