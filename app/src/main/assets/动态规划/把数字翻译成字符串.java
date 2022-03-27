package com.ng.code.menu.动态规划;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 * 现在给一串数字，返回有多少种可能的译码结果
 * 数据范围：字符串长度满足 0 < n \le 900<n≤90
 * 进阶：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 *
 * 示例:
 * 示例1
 * 输入：
 * "12"
 * 返回值：
 * 2
 *
 * 说明：
 * 2种可能的译码结果（”ab” 或”l”）
 * 示例2
 * 输入：
 * "31717126241541717"
 * 返回值：
 * 192
 *
 * 说明：
 * 192种可能的译码结果
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 把数字翻译成字符串 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.solve("31717126241541717"));
    }

    private static class EasySolution {

        public static int solve(String nums) {
            //用来保留前面两个字符位置对应的可能译码结果数
            int first = 1, second = 1;
            for (int i = 0; i < nums.length(); i++) {
                //遇到0不能译码，second清零
                if (nums.charAt(i) == '0') {
                    second = 0;
                }
                //更新前两个字符对应的值
                //符合条件则可以译1个或者2个数字
                if (i >= 1 && Integer.parseInt(nums.substring(i - 1, i + 1)) <= 26) {
                    second = first + second;
                    first = second - first;
                }
                //只能译1个数字
                else {
                    first = second;
                }
            }
            return second;
        }


    }

    private static class HardSolution {

    }

}
