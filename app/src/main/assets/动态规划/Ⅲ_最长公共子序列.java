package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:
 *
 * 原题描述:
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
 *
 * 数据范围：0 \le |str1|,|str2| \le 20000≤∣str1∣,∣str2∣≤2000
 * 要求：空间复杂度 O(n^2)  时间复杂度 O(n^2)
 *
 * 示例:
 * 示例1
 * 输入：
 * "1A2C3D4B56","B1D23A456A"
 * 返回值：
 * "123456"
 *
 * 示例2
 * 输入：
 * "abc","def"
 * 返回值：
 * "-1"
 *
 * 示例3
 * 输入：
 * "abc","abc"
 * 返回值：
 * "abc"
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅲ_最长公共子序列 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.LCS("1A2C3D4B56", "B1D23A456A"));
    }

    //解法:https://blog.csdn.net/hrn1216/article/details/51534607
    private static class EasySolution {

        public static String LCS(String s1, String s2) {
            // write code here
            int str1Len = s1.length();
            int str2Len = s2.length();
            int[][] cLenNUm = new int[s1.length() + 1][s2.length() + 1];
            //默认赋值，[0][?],[?][0]默认两侧皆0,类似公式中0的场景
            //构造一个LCS长度数组
            for (int i = 1; i <= str1Len; i++) {
                for (int j = 1; j <= str2Len; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        //对应公式第二条相等
                        cLenNUm[i][j] = cLenNUm[i - 1][j - 1] + 1;
                    } else {
                        //对应公式第三条不相等
                        cLenNUm[i][j] = Math.max(cLenNUm[i][j - 1], cLenNUm[i - 1][j]);
                    }
                }
            }

            LogUtil.print(cLenNUm);

            //反推结果
            int i = str1Len;
            int j = str2Len;
            StringBuffer sb = new StringBuffer();//作为结果
            while (i > 0 && j > 0) {//这里其实处理了i=0,j=0的，对应公式0的反推场景
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {//反推公式中不相等的场景
                    //该值一定是被选取到的，根据之前的公式，知道两条字符串的下标都前进一位
                    sb.append(s1.charAt(i - 1));
                    i--;
                    j--;
                } else {//对应公式中不相等的反推场景
                    if (cLenNUm[i][j - 1] > cLenNUm[i - 1][j]) {//找大的那个方向，此处是左边大于上面，则该处的结果是来自左边
                        j--;
                    } else if (cLenNUm[i][j - 1] < cLenNUm[i - 1][j]) {
                        i--;
                    } else if (cLenNUm[i][j - 1] == cLenNUm[i - 1][j]) {
                        //对于有分支的可能时，我们选取单方向
                        j--;   //此结果对于结果1所选取方向，str1的下标左移一位.替换为j--，则结果对应与结果2选取的方向
                    }
                }
            }
            //由于是从后往前加入字符的，需要反转才能得到正确结果
            return sb.reverse().toString();
        }
    }

    private static class HardSolution {

    }

}
