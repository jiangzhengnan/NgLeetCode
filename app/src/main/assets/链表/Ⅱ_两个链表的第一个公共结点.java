package com.ng.code.menu.链表;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=295&tqId=23257&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 */
@Solution(easy = 1, hard = 1, partice = 3)
public class Ⅱ_两个链表的第一个公共结点 {

    public static void main(String[] args) {
        ListNode phead1 = ListNode.getNodeList(1, 2, 3);
        ListNode phead2 = ListNode.getNodeList(4, 5);
        ListNode phead3 = ListNode.getNodeList(6, 7);

        ListNode.mergeNodeList(phead1, phead3);
        ListNode.mergeNodeList(phead2, phead3);

        LogUtil.print(EasySolution.FindFirstCommonNode(phead1, phead2));
    }

    /**
     * 双指针
     * 因为 ta + tb = tb + ta
     */
    private static class EasySolution {
        public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
            if (pHead1 == null || pHead2 == null)  //其中有一个为空，则不能有公共结点，返回null
                return null;
            ListNode ta = pHead1;
            ListNode tb = pHead2;
            while (ta != tb) {
                ta = (ta == null ? pHead2 : ta.next);
                tb = (tb == null ? pHead1 : tb.next);
            }
            return ta;
        }
    }

    private static class HardSolution {
    }


}
