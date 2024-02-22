package com.ng.code.menu.双指针;

import java.util.Arrays;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/reverse-string/
 * 原题描述:
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 示例:
 * 输入：
 * "abcd"
 * 返回值：
 * "dcba"
 */
@Solution(easy = 1, hard = 1, particle = 1)
public class Ⅰ_反转字符串 {

    public static void main(String[] args) {
        char[] data = "abcd".toCharArray();
        EasySolution.reverseString(data);
        LogUtil.print(Arrays.toString(data));
    }

    private static class EasySolution {
        public static void reverseString(char[] s) {
            int length = s.length;
            for (int i = 0; i < length / 2; i++) {
                char temp = s[i];
                s[i] = s[length - 1 - i];
                s[length - 1 - i] = temp;
            }
        }
    }

    private static class HardSolution {

    }

}
