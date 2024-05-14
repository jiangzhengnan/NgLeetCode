package com.ng.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {
        LogUtil.print(swapPairs(ListNode.getNodeList(1, 2, 3, 4)));
    }

    public ListNode swapPairs(ListNode head) {
        //分
        ListNode slow = head;
        ListNode fast = head;


        //治

        return newHead;
    }


    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}


/*
https://leetcode.cn/problems/swap-nodes-in-pairs/description/?envType=study-plan-v2&envId=top-100
-liked-100-liked-liked

给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：

输入：head = []
输出：[]
示例 3：

输入：head = [1]
输出：[1]

提示：

链表中节点的数目在范围 [0, 100] 内
0 <= Node.val <= 100
 */
