package com.ng.code;

import com.ng.code.util.LogUtil;

/**
 * 输入：
 * [1,-2,3,10,-4,7,2,-5]
 * 返回值：
 * 18
 */
public class PracticeClass {
    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        String a = "abcd";

        LogUtil.pring(testClass.decodeString("3(a2(c))3(a)2(bc)"));
        //18
    }

    /**
     * 递归
     */
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] sArray = s.toCharArray();

        String res = "";
        int index = 0;
        while (index < s.length() && !isNumber(sArray[index])) {
            res += sArray[index++];
        }
        if (index >= s.length()) {
            return res;
        }
        int number = sArray[index] - '0';
        index++;
        int kNumber = 0;
        int start = index;
        while (index < sArray.length) {
            char temp = sArray[index];
            if (temp == '(') {
                kNumber++;
            } else if (temp == ')') {
                kNumber--;
            }
            if (kNumber == 0) {
                break;
            }
            index++;
        }
        for (int i = 0; i < number; i++) {
            res += decodeString(s.substring(start + 1, index));
        }
        if (index + 1 < s.length()) {
            res += decodeString(s.substring(index + 1));
        }
        return res;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

//    /**
//     * 迭代
//     */
//    public String decodeString(String s) {
//        String res = "";
//        Stack<Character> data = new Stack<>();
//        int index = 0;
//
//        return res;
//    }

}



