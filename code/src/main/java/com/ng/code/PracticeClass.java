package com.ng.code;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

public class PracticeClass {

    public static void main(String[] args) {
        PracticeClass test = new PracticeClass();
        TreeNode data = TreeNode.createTreeNode(4, 2, 7, 1, 3, 6, 9);
        ListNode listNode = ListNode.getNodeList(1, 2, 3, 4, 5);
        int[][] matrix = new int[][]{
          {1, 3, 1},
          {1, 5, 1},
          {4, 2, 1}
        };
        char[][] stringsData = new char[][]{
          {'a', 'b'},
          {'c', 'd'},
          };
        char[][] stringsData2 = new char[][]{
          {'A', 'B', 'C', 'E'},
          {'S', 'F', 'C', 'S'},
          {'A', 'D', 'E', 'F'}
        };
        int[] arrayData = new int[]{2, 3, 1, 1, 4};
        int[] arrayData2 = new int[]{2, 0};

        LogUtil.pring(test.canJump(arrayData2));
    }

    public boolean canJump(int[] nums) {
        //某一位是否能从第一位到达
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {

            boolean canJump = false;
            for (int j = 0; j < i; j++) {
                if (dp[j] && (  j >= i - nums[j])) {
                    canJump = true;
                    break;
                }
            }
            if (canJump) {
                dp[i] = true;
            }

        }


        return dp[nums.length - 1];
    }

}













