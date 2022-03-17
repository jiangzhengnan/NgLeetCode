package com.ng.code.menu.二叉树;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

import java.util.HashMap;

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
@Solution(easy = 0, hard = 0)
public class 输出二叉树的右视图 {

    public static void main(String[] args) {
        LogUtil.pring(EasySolution.solve(new int[]{1, 2, 4, 5, 3},
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

        private static HashMap<Integer, Integer> ans = new HashMap<>();
        private static HashMap<Integer, Integer> map = new HashMap<>();
        private static int j = 0;

        public static int[] solve(int[] xianxu, int[] zhongxu) {
            //将xianxu节点的值映射到相应的中序节点的下标。
            for (int i = 0; i < zhongxu.length; i++) {
                map.put(zhongxu[i], i);
            }
            //开始构建二叉树。
            build(xianxu, zhongxu, 0, xianxu.length - 1, 0);

            //创建放回答案的数组
            int[] temp = new int[ans.size()];

            //将值传入数组。
            for (int i = 0; i < ans.size(); i++) {
                temp[i] = ans.get(i);
            }

            return temp;
        }

        public static void build(int[] xianxu, int[] zhongxu, int left, int right, int i) {
            if (left > right) {
                return;
            }
            int index = map.get(xianxu[j++]);
            //构建左子树
            build(xianxu, zhongxu, left, index - 1, i + 1);
            //构建右子树
            build(xianxu, zhongxu, index + 1, right, i + 1);
            //存储第i层的最右边的那个节点。
            ans.put(i, zhongxu[index]);
        }

    }

    private static class HardSolution {

    }

}
