package com.ng.code.menu;

import com.ng.code.util.ListNode;
import com.ng.code.util.LogUtil;
import com.ng.code.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Ⅰ Ⅱ Ⅲ
 */
public class PracticeClass {

    public static void main(String[] args) {

        String strvalue =null;
        LogUtil.pring(parseInt(strvalue));
    }

    public static int parseInt(String aValue) {
        return parseInt(aValue, 0);
    }


    public static int parseInt(String aValue, int aDefault){
        if (aValue == null || aValue.length() == 0)
            return aDefault;
        int result = aDefault;
        boolean isHex = false;
        if(isHex = aValue.startsWith("0x")){
            aValue = aValue.substring(2);
        }
        try {
            if (!isHex) {
                result = Integer.parseInt(aValue);
            } else {
                result = (int) Long.parseLong(aValue, 16);
            }
        }
        catch (Exception ex) {
            //#if (debug == true)
            //#endif
        }
        return result;
    }


}
