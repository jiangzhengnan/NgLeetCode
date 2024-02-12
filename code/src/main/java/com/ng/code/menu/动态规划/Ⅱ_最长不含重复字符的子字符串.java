package com.ng.code.menu.动态规划;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;

/**
 * Ⅰ Ⅱ Ⅲ
 * link:https://www.nowcoder.com/practice/48d2ff79b8564c40a50fa79f9d5fa9c7?tpId=13&tqId=2276769&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
 * 原题描述:
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * <p>
 * 示例:
 * 输入：
 * "abcabcbb"
 * 返回值：
 * 3
 * 说明：
 * 因为无重复字符的最长子串是"abc"，所以其长度为 3。
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_最长不含重复字符的子字符串 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.lengthOfLongestSubstring("abcabcbb"));

    }

    /**
     * 动态规划+哈希表，看到很多题解都用了数组来保存当前为结尾的最长结果，
     * 其实可以更加优化。当出现重复字符时，只需要更新最近的相同字符作为长度计算起点，然后每次更新最长不同字符字串长度值即可。
     */
    private static class EasySolution {

        static int max;

        public static int lengthOfLongestSubstring(String s) {
            int start = -1, sub = 1;
            //哈希表存储字符和字符位置的对应关系
            HashMap<Character,Integer> chara = new HashMap<Character,Integer>();
            //遍历字符串
            for(int i = 0;i < s.length();i++){
                if(chara.containsKey(s.charAt(i))){
                    //更新最近的相同字符作为长度计算起点
                    start = Math.max(start,chara.get(s.charAt(i)));
                }
                chara.put(s.charAt(i),i);
                //更新最长不同字符字串长度值
                sub = Math.max(sub,i - start);
            }
            return sub;

        }

    }

    /**
     * dp[i]表示的是以i结尾的最长不含重复字符的子字符串。使用了hashmap这个数据结构记录<char,index>
     * 如果map中没有当前这个元素，那么dp[i]=dp[i-1]+1
     * 如果map中存在当前的元素，一开始的想法是 dp[i]=i-map.get(array[i]),但是这样想有点问题，
     * 如果当前的字符串是abba的时候,按照刚才的思路dp[0]=1 dp[1]=2 dp[2]=1 dp[3]=3
     * 但是dp[3]是错误的，因为中间存在了重复的字符。所以要加一种情况。
     * dp[i]=Math.min(dp[i-1]+1,i-map.get(array[i]))
     */
    private static class HardSolution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) return 0;
            char[] array = s.toCharArray();
            if (array.length == 1) {
                return 1;
            }
            int[] dp = new int[array.length];
            int maxLength = 1;
            HashMap<Character, Integer> map = new HashMap<>();
            dp[0] = 1;
            map.put(array[0], 0);
            for (int i = 1; i < array.length; i++) {
                dp[i] = 1;
                if (!map.containsKey(array[i])) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = Math.min(dp[i - 1] + 1, i - map.get(array[i]));
                }
                map.put(array[i], i);
                maxLength = Math.max(maxLength, dp[i]);
            }
            return maxLength;
        }
    }

}
