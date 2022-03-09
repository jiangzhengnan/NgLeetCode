package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=295&tqId=23452&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）
 */
@Solution(easy = 0, hard = 0)
public class 对称的二叉树 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(1, 2, 2, 3, 4, 4, 3);
        LogUtil.pring(EasySolution.isSymmetrical(data));
        LogUtil.pring(HardSolution.isSymmetrical(data));
    }

    /**
     * 利用「层序遍历」的方式，以 “层” 为单位进行 “对称” 检查
     * 使用「层序遍历」的方式进行「逐层检查」，对于空节点使用 emptyNode 进行代指，同时确保不递归 emptyNode 对应的子节点。
     * 时间:On
     * 空间:On
     */
    private static class EasySolution {
        static int INF = 0x3f3f3f3f;
        static TreeNode emptyNode = new TreeNode(INF);

        static boolean isSymmetrical(TreeNode root) {
            if (root == null) return true;

            Deque<TreeNode> d = new ArrayDeque<>();
            d.add(root);
            while (!d.isEmpty()) {
                // 每次循环都将下一层拓展完并存到「队列」中
                // 同时将该层节点值依次存入到「临时列表」中
                int size = d.size();
                List<Integer> list = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode poll = d.pollFirst();
                    if (!poll.equals(emptyNode)) {
                        d.addLast(poll.left != null ? poll.left : emptyNode);
                        d.addLast(poll.right != null ? poll.right : emptyNode);
                    }
                    list.add(poll.val);
                }

                // 每一层拓展完后，检查一下存放当前层的该层是否符合「对称」要求
                if (!check(list)) return false;
            }
            return true;
        }

        // 使用「双指针」检查某层是否符合「对称」要求
        static boolean check(List<Integer> list) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                if (!list.get(l).equals(list.get(r))) return false;
                l++;
                r--;
            }
            return true;
        }
    }

    /**
     * 利用「递归树展开」的方式，以 “子树” 为单位进行 “对称” 检查
     * 若满足对称二叉树，必须满足：
     * 1.两棵子树根节点值相同；
     * 2.两颗子树的左右子树分别对称，包括：
     * a 树的左子树与 b 树的右子树相应位置的值相等
     * a 树的右子树与 b 树的左子树相应位置的值相等
     * 时间:On
     * 空间:On
     */
    private static class HardSolution {
        static public boolean isSymmetrical(TreeNode root) {
            return check(root, root);
        }

        static boolean check(TreeNode a, TreeNode b) {
            if (a == null && b == null) return true;
            if (a == null || b == null) return false;
            if (a.val != b.val) return false;
            return check(a.left, b.right) && check(a.right, b.left);
        }
    }

}
