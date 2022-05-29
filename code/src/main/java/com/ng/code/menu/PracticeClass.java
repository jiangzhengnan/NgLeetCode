package com.ng.code.menu;


import android.text.TextUtils;

import androidx.annotation.Nullable;

/**
 * 不会的:
 * Ⅱ_输出二叉树的右视图
 * <p>
 * Ⅱ_最长不含重复字符的子字符串
 */
public class PracticeClass {

    public static void main(String[] args) {
        String className = "com/ng/xerath/func/DataMethodUtil";
        System.out.println(getPkgNameFromOwner(className));
        System.out.println(getClassNameFromOwner(className));

    }

    /**
     * 传入格式:
     * owner: com/ng/xerath/func/DataMethodUtil
     * 返回格式:
     * com.ng.xerath.func
     */
    public static String getPkgNameFromOwner(@Nullable String owner) {
        if (owner == null || owner.length() == 0 || !owner.contains("/")) {
            return "";
        }
        String[] tempArray = owner.split("/");
        int classNameStrLength = tempArray[tempArray.length - 1].length();
        return owner.replaceAll("/", ".")
                    .substring(0, owner.length() - classNameStrLength - 1);
    }

    /**
     * 传入格式:
     * owner: com/ng/xerath/func/DataMethodUtil
     * 返回格式:
     * DataMethodUtil
     */
    public static String getClassNameFromOwner(@Nullable String owner) {
        if (owner == null || owner.length() == 0 || !owner.contains("/")) {
            return "";
        }
        String[] tempArray = owner.split("/");
        int classNameStrLength = tempArray[tempArray.length - 1].length();
        return owner.replaceAll("/", ".")
                    .substring(owner.length() - classNameStrLength, owner.length());
    }

}

