package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;
import com.ng.code.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=295&tqId=23455&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 描述
 * 请实现两个函数，分别用来序列化和反序列化二叉树，不对序列化之后的字符串进行约束，但要求能够根据序列化之后的字符串重新构造出一棵与原二叉树相同的树。
 * 二叉树的序列化(Serialize)是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树等遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）
 * 二叉树的反序列化(Deserialize)是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 * 示例1
 * 输入：
 * {1,2,3,#,#,6,7}
 * 返回值：
 * {1,2,3,#,#,6,7}
 * 说明：
 * 如题面图
 */
@Solution(easy = 0, hard = 0)
public class 序列化二叉树 {

    public static void main(String[] args) {
        TreeNode data = TreeNode.createTreeNode(1, 2, 3, -1, -1, 6, 7);
        String sData = EasySolution.serialize(data);
        LogUtil.pring("序列化:" + sData);

        TreeNode reData = EasySolution.deserialize(sData);
        LogUtil.pring("反序列化:");
        reData.print();

    }

    /**
     * 我们使用 0x3f3f3f3f 作为无效值（当然也可以不使用数字，使用某个特殊字符进行表示，只要能在反序列时有所区分即可），并建立占位节点 emptyNode 用来代指空节点（emptyNode.val = INF）。
     *
     * 序列化：先特判掉空树的情况，之后就是常规的层序遍历逻辑：
     *
     * 1.起始时，将 root 节点入队；
     * 2.从队列中取出节点，检查节点是否有左/右节点：
     * 如果有的话，将值追加序列化字符中（注意使用分隔符），并将节点入队；
     * 如果没有，检查当前节点是否为 emptyNode 节点，如果不是 emptyNode 说明是常规的叶子节点，需要将其对应的空节点进行存储，即将 emptyNode 入队；
     * 3.循环流程 22，直到整个队列为空。
     *
     * 反序列：同理，怎么「序列化」就怎么进行「反序列」即可：
     * 1.起始时，构造出 root 并入队；
     * 2.每次从队列取出元素时，同时从序列化字符中截取两个值（对应左右节点），检查是否为 INF，若不为 INF 则构建对应节点；
     * 3.循环流程 22，直到整个序列化字符串被处理完（注意跳过最后一位分隔符）。
     */
    private static class EasySolution {

        static int INF = 0x3f3f3f3f;
        static TreeNode emptyNode = new TreeNode(INF);

        public static String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder sb = new StringBuilder();
            // 使用队列进行先序遍历，起始先将 root 放入队列
            Deque<TreeNode> d = new ArrayDeque<>();
            d.addLast(root);
            while (!d.isEmpty()) {
                // 每次从队列中取出元素进行「拼接」，包括「正常节点」和「叶子节点对应的首位空节点」
                TreeNode poll = d.pollFirst();
                sb.append(poll.val + "_");
                // 如果取出的节点不为「占位节点」，则继续往下拓展，同时防止「占位节点」不继续往下拓展
                if (!poll.equals(emptyNode)) {
                    d.addLast(poll.left != null ? poll.left : emptyNode);
                    d.addLast(poll.right != null ? poll.right : emptyNode);
                }
            }
            return sb.toString();
        }

        public static TreeNode deserialize(String data) {
            if (data.equals("")) return null;

            // 根据分隔符进行分割
            String[] ss = data.split("_");
            int n = ss.length;
            // 怎么序列化就怎么反序列化
            // 使用队列进行层序遍历，起始先将 root 构建出来，并放入队列
            TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
            Deque<TreeNode> d = new ArrayDeque<>();
            d.addLast(root);
            for (int i = 1; i < n - 1; i += 2) {
                TreeNode poll = d.pollFirst();
                // 每次从中取出左右节点对应 val
                int a = Integer.parseInt(ss[i]), b = Integer.parseInt(ss[i + 1]);
                // 如果左节点对应的值不是 INF，则构建「真实节点」
                if (a != INF) {
                    poll.left = new TreeNode(a);
                    d.addLast(poll.left);
                }
                // 如果右节点对应的值不是 INF，则构建「真实节点」
                if (b != INF) {
                    poll.right = new TreeNode(b);
                    d.addLast(poll.right);
                }
            }
            return root;
        }
    }

    /**
     * 无
     */
    private static class HardSolution {

    }

}
