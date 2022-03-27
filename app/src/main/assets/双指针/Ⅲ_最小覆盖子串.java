package com.ng.code.menu.双指针;

import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:
 * 原题描述:
 * 给出两个字符串 s 和 t，要求在 s 中找出最短的包含 t 中所有字符的连续子串。
 * 数据范围：0 \le |S|,|T| \le100000≤∣S∣,∣T∣≤10000，保证s和t字符串中仅包含大小写英文字母
 * 要求: 时间复杂度 O(n)
 * 例如：
 * S ="XDOYEZODEYXNZ"S="XDOYEZODEYXNZ"
 * T ="XYZ"T="XYZ"
 * 找出的最短子串为"YXNZ""YXNZ".
 *
 * 注意：
 * 如果 s 中没有包含 t 中所有字符的子串，返回空字符串 “”；
 * 满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
 * 示例:
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅲ_最小覆盖子串 {

    public static void main(String[] args) {

    }

    //暴力
    private static class EasySolution {

        public String minWindow(String S, String T) {
            //n为S的长度，m为T的长度
            int n = S.length();
            int m = T.length();
            //记录子串长度
            int len = 10001;
            //记录子串起始点
            int start = 0;
            //统计T中字符出现次数
            int[] map1 = new int[128];
            for (int i = 0; i < m; i++) {
                map1[T.charAt(i)]++;
            }
            for (int i = 0; i < n; i++) {
                //统计子串字符出现次数
                int[] map2 = new int[128];
                for (int j = i; j < n; j++) {
                    map2[S.charAt(j)]++;
                    //如果合法，并且长度小于len
                    if (match(map1, map2) && len > j - i + 1) {
                        //更新len和起始点
                        len = j - i + 1;
                        start = i;
                    }
                }
            }
            return len == 10001 ? "" : S.substring(start, start + len);
        }

        //判断子串是否包含T中所有字符
        private boolean match(int[] map1, int[] map2) {
            for (int i = 0; i < 128; i++) {
                //只要某个字符，T中出现次数更多，则不合法
                if (map1[i] > map2[i]) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * 双指针滑动窗口
     * 首先统计T中所有字符出现次数，记录在map中。
     * 定义两个变量l和r分别记录子串的起点和终点。定义一个变量count用于统计T中字符是否能在对应子串完全消除，如果能，则子串合法，否则，不合法。
     * 如果右边界对应字符次数大于0，说明需要消去当前字符，r右移，map对应次数减1，同时count减1。只要count为0时，说明字符全部消除掉，
     * 当前子串合法，跟新子串长度和起始点。如果左边界对应字符次数等于0，说明需要重新消去当前字符，l右移，右移之后，
     * map对应次数加1，计数count也需要加1。
     */
    private static class HardSolution {

        public String minWindow(String S, String T) {
            //n为S的长度，m为T的长度
            int n = S.length();
            int m = T.length();
            //记录子串长度
            int len = 10001;
            //记录子串起始点
            int start = 0;
            //统计T中字符出现次数
            int[] map = new int[128];
            for (int i = 0; i < m; i++) {
                map[T.charAt(i)]++;
            }
            //滑动窗口的左右边界l和r，以及待消除的字符数量count
            int l = 0, r = 0, count = m;
            while (r < n) {
                //如果出现次数大于0，说明需要消除，count减1
                if (map[S.charAt(r++)]-- > 0) {
                    count--;
                }
                //只要count为0，说明当前子串合法
                while (count == 0) {
                    //如果长度小于len，则更新len和start
                    if (len > r - l) {
                        len = r - l;
                        start = l;
                    }
                    //如果左边界字符出现次数为0（要么小于0，要么等于0），则需要重新消除，后移l
                    if (map[S.charAt(l++)]++ == 0) {
                        //后移之后，count需加1
                        count++;
                    }
                }
            }
            return len == 10001 ? "" : S.substring(start, start + len);
        }


    }

}
