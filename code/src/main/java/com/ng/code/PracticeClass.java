package com.ng.code;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;

public class PracticeClass {

    private void test() {
        LogUtil.print(plusOne(new int[]{9, 9, 9}));
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        digits[n - 1]++;
        if (digits[n - 1] < 10) {
            return digits;
        }
        int yu = 0;
        int index = 0;
        while (index < n) {
            int cur = digits[n - 1 - index] + yu;
            yu = cur / 10;
            digits[n - 1 - index] = cur % 10;
            index++;
        }
        if (yu > 0) {
            int[] newDig = new int[n + 1];
            newDig[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                newDig[i + 1] = digits[i];
            }
            return newDig;
        } else {
            return digits;
        }

    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}
