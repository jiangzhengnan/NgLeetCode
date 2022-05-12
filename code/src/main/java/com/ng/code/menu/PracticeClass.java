package com.ng.code.menu;


import com.ng.code.util.LogUtil;

import java.util.HashMap;

/**
 * 不会的:
 * Ⅱ_输出二叉树的右视图
 * <p>
 * Ⅱ_最长不含重复字符的子字符串
 */
public class PracticeClass {

    public static void main(String[] args) {
        LogUtil.pring(lengthOfLongestSubstring("df"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int start = -1;
        int ub = 1;
        HashMap<Character, Integer> chara = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (chara.containsKey(s.charAt(i))) {
                start = Math.max(start, chara.get(s.charAt(i)));
            }
            chara.put(s.charAt(i), i);
            ub = Math.max(ub, i - start);
        }
        return ub;
    }

}

