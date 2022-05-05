package com.ng.code.menu;


import com.ng.code.util.LogUtil;

import java.util.ArrayList;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/02bf49ea45cd486daa031614f9bd6fc3?tpId=295&tqId=1073463&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
 * 注意是节点的编号而非节点的数值。
 * <p>
 * 数据范围：节点数量满足 0 <= n <= 10^50≤n≤10
 * 5
 * ，节点中的值都满足 0 <= val <= 10000≤val≤1000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 */
public class PracticeClass {

    public static void main(String[] args) {
        String data = "abc";
        //"abc","acb","bac","bca","cab","cba"
        LogUtil.pring(Permutation(data).toString());
    }

    static ArrayList<String> res = new ArrayList<>();

    public static ArrayList<String> Permutation(String str) {
        boolean[] visited = new boolean[str.length()];
        recursion(str, "", visited);
        return res;
    }

    private static void recursion(String str, String cur, boolean[] visited) {
        if (str.length() == cur.length() && !res.contains(cur)) {
            res.add(cur);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!visited[i]) {
                cur += str.charAt(i);
                visited[i] = true;
                recursion(str, cur, visited);
                cur = cur.substring(0, cur.length() - 1);
                visited[i] = false;
            }
        }
    }


}

/**
 * Ⅱ_字符串的排列
 * <p>
 * Ⅲ_堆排序
 * Ⅲ_数组中的逆序对
 */
