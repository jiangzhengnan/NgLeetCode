package com.ng.code.menu.二叉树;

import com.ng.base.utils.LogUtil;
import com.ng.code.util.Solution;
import com.ng.base.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/c9480213597e45f4807880c763ddd5f0?tpId=295&tqId=1073834&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
 * 示例1
 * 输入：
 * [1,2,4,5,3],[4,2,5,1,3]
 * 返回值：
 * [1,3,5]
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_输出二叉树的右视图 {

    public static void main(String[] args) {
        LogUtil.print(EasySolution.solve(new int[]{1, 2, 4, 5, 3},
                                         new int[]{4, 2, 5, 1, 3}));
    }

    /**
     * 1.答案数组的大小其实是对应二叉树的高度，或者说这二叉树有多少层。
     * 2.二叉树每层只能有一个顶点，这个顶点就是二叉树该层最右边的节点
     * 3.我构建二叉树是递归的构建左子树和右子树，这样左子树的最右子节点会被右子树的最右子节点给覆盖掉(前提是该节点要在这二叉树的同一层)
     * 4.当我们构建完二叉树后，答案也构建好了，我们这要将答案写入放回数组就行。
     * 这里说明下我为什么使用hashmap来做，本来使用ArrayList，但是如果调用set方法时， arraylist的size=0，你set的第一次参数index > size 就会报IndexOutOfBoundsException，具体看文档。所以最后我选择使用了map来做。
     */
    private static class EasySolution {

        public static HashMap<Integer, Integer> ans = new HashMap<>();

        static int level = 0;

        public static int[] solve(int[] preorder, int[] inorder) {
            List<Integer> preList = new ArrayList<>();
            List<Integer> inList = new ArrayList<>();
            for (int i = 0; i < preorder.length; i++) {
                preList.add(preorder[i]);
                inList.add(inorder[i]);
            }
            build(preList, inList, 0);
            //创建放回答案的数组
            int[] temp = new int[ans.size()];

            //将值传入数组。
            for (int i = 0; i < ans.size(); i++) {
                temp[i] = ans.get(i);
            }
            return temp;
        }


        private static TreeNode build(List<Integer> preList, List<Integer> inList, int level) {
            if (inList.size() == 0) {
                return null;
            }
            int rootVal = preList.remove(0);

            TreeNode root = new TreeNode(rootVal);


            int mid = inList.indexOf(rootVal);
            root.left = build(preList, inList.subList(0, mid), level + 1);
            root.right = build(preList, inList.subList(mid + 1, inList.size()), level + 1);
            //存储第i层的最右边的那个节点。
            ans.put(level, root.val);
            return root;
        }

    }

    private static class HardSolution {

    }

}
