package com.ng.code.menu.排序;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/04/16
 * @description :
 * 算法复杂度：O(nlogn)
 * 算法空间复杂度：O(n)
 * 算法稳定性：稳定
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_归并排序 {

    public static void main(String[] args) {
        int[] array = new int[]{9, 8, 7, 3, 2, 1, 4, 5, 6};
        sort(array);
        LogUtil.pring(array);
    }

    /**
     * 分
     * 治 排序
     *
     */
    private static void sort(int[] array) {


    }
}
