package com.ng.code;

import com.ng.code.util.LogUtil;

public class PracticeClass {

    public static void main(String[] args) {
        int[] data = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(data);
        LogUtil.pring(data);
    }

    /**
     * 第一个元素默认已排序
     * 遍历0到length-1的下标元素
     * 每个元素每一次从i到0位置遍历，遇到比自己大的就后挪
     * ，比自己小的就留在当前位置
     */
    private static void sort(final int[] data) {
    }


}



