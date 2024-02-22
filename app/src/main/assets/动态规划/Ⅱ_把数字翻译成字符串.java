package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
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
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_把数字翻译成字符串 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.solve("31717126241541717"));
    }

    /**
     * 对于普通数组1-9，译码方式只有一种，但是对于11-19，21-26，译码方式有可选择的两种方案，因此我们使用动态规划将两种方案累计。
     *
     * step 1：用辅助数组dp表示前i个数的译码方法有多少种。
     * step 2：对于一个数，我们可以直接译码它，也可以将其与前面的1或者2组合起来译码：如果直接译码，则dp[i]=dp[i-1]；如果组合译码，则dp[i]=dp[i-2]。
     * step 3：对于只有一种译码方式的，选上种dp[i-1]即可，对于满足两种译码方式（10，20不能）则是dp[i-1]+dp[i-2]
     * step 4：依次相加，最后的dp[length]即为所求答案。
     *
     * 当前字符不等于0的时候,dp[i] = dp[i-1],此时将当前位置的一个字符译码
     * 当前字符+前一个字符，记为num, 如果 10<=num<=26 此时符合两个合并一起译码的条件；
     * 若此时i等于1，直接dp[i]++;
     * 大于1, 则dp[i] += dp[i-2];
     * 举个例子： nums = "324"
     * 此时dp[0] = 1, dp[1]呢？ dp[2]呢？
     * 很明显nums[1] != '0'，所以dp[1] = dp[0],num = 32，此时不满足两个一起译码的条件则循环往下执行，此时 nums[2] != '0',则 dp[2] = dp[1] = 1, num = 24，此时满足两个一起译码的条件,因为i==2大于1，所以dp[2] += dp[2-2] ,dp[2] = 1+1 = 2。
     */
    private static class EasySolution {

        public static int solve(String nums) {
            if (nums.length() == 0 || nums.charAt(0) == '0')
                return 0;
            int[] dp = new int[nums.length()];
            dp[0] = 1;
            for (int i = 1; i < dp.length; i++) {
                if (nums.charAt(i) != '0') {
                    dp[i] = dp[i - 1];
                }
                //  3 2 4
                int num = (nums.charAt(i - 1) - '0') * 10 + (nums.charAt(i) - '0');
                if (num >= 10 && num <= 26) {
                    if (i == 1) {
                        dp[i] += 1;
                    } else {
                        dp[i] += dp[i - 2];
                    }
                }
            }
            return dp[nums.length() - 1];

        }


    }

    private static class HardSolution {

    }

}
