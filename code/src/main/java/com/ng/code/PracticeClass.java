package com.ng.code;

import java.util.HashMap;
import java.util.Map;

import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

public class PracticeClass {

    //nowtodo: 130
    public static void main(String[] args) {
        PracticeClass test = new PracticeClass();
        TreeNode data = TreeNode.createTreeNode(3, 9, 20, -1, -1, 15, 7);

        int[] array1 = new int[]{100, 4, 200, 1, 3, 2};
        int[] array2 = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        LogUtil.pring(test.longestConsecutive(array1));
        LogUtil.pring(test.longestConsecutive(array2));

    }

    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> tong = new HashMap<>();
        for (int temp : nums) {
            tong.put(temp, 1);
            int bigger = temp;
            while (tong.containsKey(bigger + 1)) {
                bigger++;
            }
            bigger -= temp;
            int litter = temp;
            while (tong.containsKey(litter - 1)) {
                litter--;
            }
            litter = temp - litter;
            res = Math.max(res, bigger + litter + 1);
        }


        return res;
    }
}













