package com.ng.code;

import com.ng.code.menu.链表.Ⅲ_单链表的排序;
import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给你一个sn x ns矩阵smatrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * <p>
 * 你必须找到一个内存复杂度优于sO(n2) 的解决方案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * 输出：13
 * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 */
public class PracticeClass {

    //nowtodo: 240 , 还差15个题
    //https://leetcode.cn/problem-list/2ckc81c/?difficulty=MEDIUM&page=1
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        LogUtil.pring(kthSmallest(matrix, 8));
    }


    public static int kthSmallest(int[][] matrix, int k) {
        int hor =    k % matrix[0].length  - 1;
        int ver = k / matrix.length;

        LogUtil.pring(ver +" " + hor);
        return matrix[ver][hor];
    }

}













