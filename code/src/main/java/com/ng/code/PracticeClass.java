package com.ng.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ng.base.utils.ListNode;
import com.ng.base.utils.LogUtil;
import com.ng.code.menu.哈希.Ⅱ_三数之和;

public class PracticeClass {

    private void test() {
        LogUtil.print(canJump(new int[]{3, 2, 1, 0, 4})); //false

    }

    // 用一个数组表示该位置能否从第一个节点触达。
    public static boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
        for (int i = 0; i <= k; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }

    public static void main(String[] args) {
        PracticeClass testClass = new PracticeClass();
        testClass.test();
    }

}

/*


 */
