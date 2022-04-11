package com.ng.code.menu.链表;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashSet;
import java.util.Set;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/71cef9f8b5564579bf7ed93fbe0b2024?tpId=295&tqId=663&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为1 \to 2\to 3\to 3\to 4\to 4\to51→2→3→3→4→4→5, 返回1\to 2\to51→2→5.
 * 给出的链表为1\to1 \to 1\to 2 \to 31→1→1→2→3, 返回2\to 32→3.
 * <p>
 * 数据范围：链表长度 0 \le n \le 100000≤n≤10000，链表中的值满足 |val| \le 1000∣val∣≤1000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 示例1
 * 输入：
 * {1,2,2}
 * 复制
 * 返回值：
 * {1}
 */
@Solution(easy = 0, hard = 1)
public class Ⅱ_删除有序链表中重复的元素二 {

    public static void main(String[] args) {
        ListNode data = ListNode.getNodeList(1, 2, 3, 3, 4, 4, 5);
        LogUtil.pring(HardSolution.deleteDuplicates(data));
    }

    /**
     * 双指针解法
     */
    private static class EasySolution {

    }

    /**
     * 迭代解法
     * pre now next
     * 如果now和next相等，则找到不相等的下一个为止
     * 然后链接pre和下一个
     */
    private static class HardSolution {
        public static ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode ya = new ListNode(-1);
            ya.next = head;
            ListNode pre = ya;
            ListNode now = head;
            ListNode next;
            int temp;
            while (now != null && now.next != null) {
                next = now.next;
                if (now.val != next.val) {
                    pre = now;
                    now = next;
                } else {
                    //相等
                    temp = now.val;
                    while (now != null && now.val == temp) {
                        now = now.next;
                    }
                    pre.next = now;
                }
            }

            return ya.next;
        }
    }


}
