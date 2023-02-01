package com.ng.code;

import com.ng.code.menu.栈堆队列.Ⅱ_滑动窗口的最大值;
import com.ng.code.util.LogUtil;
import com.ng.code.util.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PracticeClass {

    public static void main(String[] args) {
        int[] array1 = new int[]{100, 4, 200, 1, 3, 2};
        int[] array2 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LogUtil.pring(longestConsecutive(array1));
    }


    public static int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();

        for (int num : nums) {
            num_set.add(num);
        }
        int res = 0;
        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int temp = 1;
                while (num_set.contains(num + 1)) {
                    num ++;
                    temp ++;
                }
                res = Math.max(temp, res);

            }
        }

        return res;
    }

}



