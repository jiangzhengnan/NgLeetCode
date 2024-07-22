package com.ng.code.menu.二叉树;

import com.ng.base.utils.TreeNode;
import com.ng.code.util.Solution;

/**
 * 字节原题
 * <p>
 * Ⅰ Ⅱ Ⅲ
 * link:https://leetcode.cn/problems/subtree-of-another-tree/
 * 原题描述:
 * <p>
 * 示例:
 */
@Solution(easy = 0, hard = 0, particle = 0)
public class Ⅱ_另一颗子树 {

    public static void main(String[] args) {
        EasySolution easySolution = new EasySolution();
        HardSolution hardSolution = new HardSolution();

    }

    private static class EasySolution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            return dfs(s, t);
        }

        public boolean dfs(TreeNode s, TreeNode t) {
            if (s == null) {
                return false;
            }
            return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
        }

        public boolean check(TreeNode s, TreeNode t) {
            if (s == null && t == null) {
                return true;
            }
            if (s == null || t == null || s.val != t.val) {
                return false;
            }
            return check(s.left, t.left) && check(s.right, t.right);
        }
    }

    private static class HardSolution {

    }

}
