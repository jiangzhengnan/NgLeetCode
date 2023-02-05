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
        LogUtil.pring(FindGreatestSumOfSubArray(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));
        //18
    }

    public static int FindGreatestSumOfSubArray(int[] array) {
        if (array == null) {
            return -1;
        }
        if (array.length == 1) {
            return array[0];
        }
        int res = Integer.MIN_VALUE;
        int now =0;
        for (int temp : array) {
            now = Math.max(now + temp, temp);
            res = Math.max(res, now);
        }
        return res;
    }

}



