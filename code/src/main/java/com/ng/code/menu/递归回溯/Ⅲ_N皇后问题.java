package com.ng.code.menu.递归回溯;

import com.ng.base.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashSet;
import java.util.Set;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/c76408782512486d91eea181107293b6?tpId=295&tqId=1008753&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 *
 * 原题描述:
 * N 皇后问题是指在 n * n 的棋盘上要摆 n 个皇后，
 * 要求：任何两个皇后不同行，不同列也不在同一条斜线上，
 * 求给一个整数 n ，返回 n 皇后的摆法数。
 * 数据范围: 1 \le n \le 91≤n≤9
 * 要求：空间复杂度 O(1)O(1) ，时间复杂度 O(n!)O(n!)
 *
 * 示例:
 * 例如当输入4时，对应的返回值为2，
 * 输入8返回92
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅲ_N皇后问题 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.Nqueen(8));
    }

    /**
     * 1.设置三个集合分别记录不能再被选中的的列col，正斜线pos，反斜线neg
     * 2.经规律发现 行号i - 列号j 可确定唯一正斜线，行号i + 列号j 可确定唯一反斜线
     * 3.符合要求的点记录当前点并递归下一个皇后，最后一个皇后成功安置后将res+1，然后需回溯回初始点将初始点删除，将初始点的皇后放置其他位置进行判断
     * 4.不符合要求的需要进行循环
     */
    private static class EasySolution {
        /**
         * @param n int整型 the n
         * @return int整型
         */
        static Set<Integer> column = new HashSet<Integer>(); //标记列不可用
        static Set<Integer> posSlant = new HashSet<Integer>();//标记正斜线不可用
        static Set<Integer> conSlant = new HashSet<Integer>();//标记反斜线不可用
        static int result = 0;

        public static int Nqueen(int n) {
            // write code here
            compute(0, n);
            return result;
        }

        private static void compute(int i, int n) {
            if (i == n) {
                result++;
                return;
            }
            for (int j = 0; j < n; j++) {
                if (column.contains(j) || posSlant.contains(i - j) || conSlant.contains(i + j)) {
                    continue;
                }
                column.add(j);//列号j
                posSlant.add(i - j);//行号i - 列号j 正斜线
                conSlant.add(i + j);//行号i + 列号j 反斜线
                compute(i + 1, n); //计算下一行
                column.remove(j); //完成上一步递归计算后，清除
                posSlant.remove(i - j);
                conSlant.remove(i + j);
            }
        }
    }

    private static class HardSolution {

    }

}
