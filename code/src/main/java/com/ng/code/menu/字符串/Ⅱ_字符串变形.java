package com.ng.code.menu.字符串;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

/**
 * link:https://www.nowcoder.com/practice/c3120c1c1bc44ad986259c0cf0f0b80e?tpId=295&tqId=44664&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 对于一个长度为 n 字符串，我们需要对它做一些变形。
 * 首先这个字符串中包含着一些空格，就像"Hello World"一样，然后我们要做的是把这个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。
 * 比如"Hello World"变形后就变成了"wORLD hELLO"。
 * 字符串中包括大写英文字母、小写英文字母、空格。
 * 进阶：空间复杂度 O(n)O(n) ， 时间复杂度 O(n)O(n)
 * 输入描述：
 * 给定一个字符串s以及它的长度n(1 ≤ n ≤ 10^6)
 * 返回值描述：
 * 请返回变形后的字符串。题目保证给定的字符串均由大小写字母和空格构成。
 *
 * 示例:
 * 输入：
 * "This is a sample",16
 * 返回值：
 * "SAMPLE A IS tHIS"
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_字符串变形 {

    public static void main(String[] args) {
        String a = "h i ";

        LogUtil.print(EasySolution.trans(a, 4));
//        LogUtil.pring(EasySolution.trans(b, 3));

    }

    private static class EasySolution {

        public static String trans(String s, int n) {
            //找出每个单词来，然后反序加入结果集
            StringBuilder sb = new StringBuilder();
            int i = n - 1;
            while (i >= 0) {//如果是空格就直接加入
                while (i >= 0 && s.charAt(i) == ' ') {
                    sb.append(" ");
                    i--;
                }
                //直到找到空格或0，将该单词放进结果集里
                if (i >= 0) {
                    StringBuilder s1 = new StringBuilder();
                    while (i >= 0 && s.charAt(i) != ' ') {
                        char cha = s.charAt(i);
                        if (cha < 'a') s1.append((char) (cha - 'A' + 'a'));  //大写转小写
                        else s1.append((char) (cha - 'a' + 'A'));  //小写转大写
                        i--;
                    }
                    sb.append(s1.reverse().toString());//将单词正过来
                }
            }

            return sb.toString();
        }

        //反转
        public static String reCode(String s) {
            String newStr = "";
            for (char temp : s.toCharArray()) {
                if (temp >= 97 && temp <= 122) {
                    //小写
                    newStr += ((char) (temp - 32));
                } else if (temp >= 65 && temp <= 90) {
                    newStr += ((char) (temp + 32));
                }

            }
            return newStr;
        }

    }

    private static class HardSolution {

    }

}
