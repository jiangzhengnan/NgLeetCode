package com.ng.code.menu;

import com.ng.code.util.LogUtil;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/03/18
 * @description :
 */
public class PracticeClass {

    public static void main(String[] args) {
        int[][] op = new int[][]{
                {1, 1, 1},
                {1, 2, 2},
                {1, 3, 2},
                {2, 1},
                {1, 4, 4},
                {2, 2}
        };
        LogUtil.pring(LRU(op, 3));

        // 1 -1

    }

    public static int[] LRU(int[][] operators, int k) {
        return null;
    }

}
