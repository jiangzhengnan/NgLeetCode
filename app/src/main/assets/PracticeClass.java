package com.ng.code.menu;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ⅰ Ⅱ Ⅲ
 */
public class PracticeClass {

    public static void main(String[] args) {
        int[][] op = new int[][]{
                {1, 1, 1},
                {1, 2, 2},
                {2, 1},
                {1, 3, 3},
                {2, 2},
                {1, 4, 4},
                {2, 1},
                {2, 3},
                {2, 4}
        };
        LogUtil.pring(LRU(op, 2));
        // 1 -1 -1 3 4

    }

    public static int[] LRU(int[][] operators, int k) {

        return null;
    }


}
