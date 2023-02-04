package com.ng.code;

import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PracticeClass {
    public static void main(String[] args) {
        LogUtil.pring(solve(new int[]{1, 2, 4, 5, 3},
                new int[]{4, 2, 5, 1, 3}));

    }

    public static HashMap<Integer, Integer> ans = new HashMap<>();

    static int level = 0;
    public static int[] solve(int[] preorder, int[] inorder) {
        List<Integer> preList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            preList.add(preorder[i]);
            inList.add(inorder[i]);
        }
        build(preList, inList,0);
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
        root.left = build(preList, inList.subList(0, mid),level+1);
        root.right = build(preList, inList.subList(mid + 1, inList.size()),level+1);
        //存储第i层的最右边的那个节点。
        ans.put(level,root.val);
        return root;
    }
}



