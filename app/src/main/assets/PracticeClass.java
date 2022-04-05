package com.ng.code.menu;

import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Ⅰ Ⅱ Ⅲ
 * 动态规划
 * 递归回溯
 * 贪心算法
 */
public class PracticeClass {

    public static void main(String[] args) {

        LogUtil.pring(jumpFloorII(2));
    }


    //f(n)=f(n-1)+f(n-2)+...+f(0)

    /**
     * f(0) = 1
     *
     */
    public static int jumpFloorII(int target) {
        if (target <= 1) {
            return target;
        }
        int result = 1;
        for (int i = 1; i < target; i++) {
            result += jumpFloorII(target - i);
        }

        return result;

    }
}
