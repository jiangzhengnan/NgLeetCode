package com.ng.code;

import java.util.ArrayList;

import com.ng.base.ListNode;
import com.ng.base.LogUtil;

public class PracticeClass {

    public static void main(String[] args) {
        LogUtil.print(mergeKLists(getData()));

    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        return null;
    }

    private static ArrayList<ListNode> getData() {
        ArrayList<ListNode> lists = new ArrayList<>();
        lists.add(ListNode.getNodeList(new int[]{1, 2}));
        lists.add(ListNode.getNodeList(new int[]{1, 4, 5}));
        lists.add(ListNode.getNodeList(new int[]{6}));
        return lists;
    }

}



