package com.ng.ngleetcode.code.链表;

import com.ng.ngleetcode.util.ListNode;
import com.ng.ngleetcode.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=295&tqId=23449&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 */
@Solution(easy = 1, hard = 0)
public class 链表中环的入口结点 {

    public static void main(String[] args) {

    }

    private static class SolutionEasy {
        //set标注法
    }

    //https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=295&tqId=23449&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
    //双指针公式法
    /*
        o     [a]     q     [b]

                                    p
                    [c]

                2*a*b = a + b + c + b
                a = c

     */
    private static class SolutionHard {

        public ListNode EntryNodeOfLoop(ListNode pHead) {
            if (pHead == null || pHead.next == null) {
                return null;
            }

            ListNode fast = pHead;
            ListNode slow = pHead;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    ListNode slow2 = pHead;
                    while ( slow2 != slow) {
                        slow2 = slow2.next;
                        slow = slow.next;
                    }
                    return slow2;
                }
            }

            return null;

        }

    }

}
