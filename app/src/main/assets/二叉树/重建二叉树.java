package com.ng.code.menu.二叉树;

import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=295&tqId=23282&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建出如下图所示。
 *
 * 示例1
 * 输入：
 * [1,2,4,7,3,5,6,8],[4,7,2,1,5,3,8,6]
 * 返回值：
 * {1,2,3,4,#,5,6,#,7,#,#,8}
 * 说明：
 * 返回根节点，系统会输出整颗二叉树对比结果，重建结果如题面图示
 *
 * 示例3
 * 输入：
 * [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
 * 返回值：
 * {1,2,5,3,4,6,7}
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class 重建二叉树 {

    public static void main(String[] args) {
        EasySolution.reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8},
                new int[]{4, 7, 2, 1, 5, 3, 8, 6}).print();

    }

    /**
     * 递归
     * 二叉树的前序遍历：根左右；中序遍历：左根右
     * 由前序遍历知道根节点之后，能在中序遍历上划分出左子树和右子树。
     * 分别对中序遍历的左右子树递归进行这一过程即可建树。
     * On On
     */
    private static class EasySolution {

        public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
            //把前序遍历的值和中序遍历的值放到list中
            List<Integer> preorderList = new ArrayList<>();
            List<Integer> inorderList = new ArrayList<>();
            for (int i = 0; i < pre.length; i++) {
                preorderList.add(pre[i]);
                inorderList.add(in[i]);
            }
            return helper(preorderList, inorderList);
        }

        private static TreeNode helper(List<Integer> preorderList, List<Integer> inorderList) {
            if (inorderList.size() == 0)
                return null;
            //前序遍历的第一个值就是根节点
            int rootVal = preorderList.remove(0);
            //创建跟结点
            TreeNode root = new TreeNode(rootVal);
            //查看根节点在中序遍历中的位置，然后再把中序遍历的数组劈两半，前面部分是
            //根节点左子树的所有值，后面部分是根节点右子树的所有值
            int mid = inorderList.indexOf(rootVal);
            //[0，mid)是左子树的所有值，inorderList.subList(0, mid)表示截取inorderList
            //的值，截取的范围是[0，mid)，包含0不包含mid。
            root.left = helper(preorderList, inorderList.subList(0, mid));
            //[mid+1，inorderList.size())是右子树的所有值，
            // inorderList.subList(mid + 1, inorderList.size())表示截取inorderList
            //的值，截取的范围是[mid+1，inorderList.size())，包含mid+1不包含inorderList.size()。
            root.right = helper(preorderList, inorderList.subList(mid + 1, inorderList.size()));
            return root;
        }
    }

    /**
     * 栈(难理解)
     * m n .. .. . .
     * 如果使用栈来解决首先要搞懂一个知识点，就是前序遍历挨着的两个值比如m和n，他们会有下面两种情况之一的关系。
     * 1，n是m左子树节点的值。
     * 2，n是m右子树节点的值或者是m某个祖先节点的右节点的值。
     * 对于第一个知识点我们很容易理解，如果m的左子树不为空，那么n就是m左子树节点的值。
     * 对于第二个问题，如果一个结点没有左子树只有右子树，那么n就是m右子树节点的值，
     * 如果一个结点既没有左子树也没有右子树，那么n就是m某个祖先节点的右节点，我们只要找到这个祖先节点就好办了。
     * 时间On
     * 空间O1
     */
    private static class HardSolution {
        public static TreeNode reConstructBinaryTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0)
                return null;
            Stack<TreeNode> s = new Stack<>();
            //前序的第一个其实就是根节点
            TreeNode root = new TreeNode(preorder[0]);
            TreeNode cur = root;
            for (int i = 1, j = 0; i < preorder.length; i++) {
                //第一种情况
                if (cur.val != inorder[j]) {
                    cur.left = new TreeNode(preorder[i]);
                    s.push(cur);
                    cur = cur.left;
                } else {
                    //第二种情况
                    j++;
                    //找到合适的cur，然后确定他的右节点
                    while (!s.empty() && s.peek().val == inorder[j]) {
                        cur = s.pop();
                        j++;
                    }
                    //给cur添加右节点
                    cur = cur.right = new TreeNode(preorder[i]);
                }
            }
            return root;
        }

    }

}
