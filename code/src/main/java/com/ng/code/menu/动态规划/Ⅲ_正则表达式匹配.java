package com.ng.code.menu.动态规划;

import com.ng.code.util.Solution;

/**
 * 原题描述:
 * 描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 1.模式中的字符'.'表示任意一个字符
 * 2.模式中的字符'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 *
 * 数据范围:
 * 1.str 只包含从 a-z 的小写字母。
 * 2.pattern 只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 * 3. 0 \le str.length \le 26 \0≤str.length≤26
 * 4. 0 \le pattern.length \le 26 \0≤pattern.length≤26
 *
 * 示例:
 * 示例1
 * 输入：
 * "aaa","a*a"
 * 返回值：
 * true
 *
 * 说明：
 * 中间的*可以出现任意次的a，所以可以出现1次a，能匹配上
 * 示例2
 * 输入：
 * "aad","c*a*d"
 * 返回值：
 * true
 *
 * 说明：
 * 因为这里 c 为 0 个，a被重复一次， * 表示零个或多个a。因此可以匹配字符串 "aad"。
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_正则表达式匹配 {

    public static void main(String[] args) {

    }

    /**
     * 动态规划
     状态定义：图片说明 表示原字符串长度为n，模式串长度为m时，是否能够匹配。
     初始化：当原串和模式串都为空时，显然能够匹配，图片说明 ;当模式串为空，而原串不为空时，均为图片说明 。
     状态转移：分模式串后一个位置是否为'*'进行讨论,为'*'时，将'*'与前一个位置合并起来进行考虑。如果后一个位置不为'*'，并且当前模式串字符和原串字符匹配，则满足图片说明 ；如果后一个位置为'*'，
     无论是否匹配，均满足dp[i][j]=dp[i][j-2] (匹配0次)，如果匹配，满足图片说明 (匹配1到无穷次或0次)
     str = 0aaa   pattern = 0a*a
        i           j

           j=0    1     2       3
     i=0  true  false  true    false
     1   false  true  true      true
     2   false  false  true    true
     3   false  false  true    true

     */
    private static class EasySolution {

        public boolean match (String str, String pattern) {
            int n=str.length();
            int m=pattern.length();
            boolean[][] dp=new boolean[n+1][m+1];

            //初始化
            dp[0][0]=true;
            for(int i=1;i<=n;i++){
                dp[i][0]=false;
            }

            //分模式串的后一个位置是否为*进行讨论,为*时，将*与前一个位置合并起来进行考虑
            for(int i=0;i<=n;i++){
                for(int j=1;j<=m;j++){
                    if(pattern.charAt(j-1)!='*'){
                        //当前模式串字符和原串字符匹配
                        if(i>0&&(str.charAt(i-1)==pattern.charAt(j-1)||(pattern.charAt(j-1)=='.'))){
                            dp[i][j]=dp[i-1][j-1];
                        }
                    }
                    else{
                        if(j>=2){
                            //不管是否匹配，都可以将当前字符绑定上*匹配原串字符0次
                            dp[i][j]=dp[i][j-2];
                            //当前模式串字符和原串字符匹配
                            if(i>0&&(str.charAt(i-1)==pattern.charAt(j-2)||(pattern.charAt(j-2)=='.'))){
                                dp[i][j]=dp[i-1][j]||dp[i][j-2];
                            }
                        }
                    }
                }
            }
            return dp[n][m];
        }

    }

    private static class HardSolution {

    }

}
