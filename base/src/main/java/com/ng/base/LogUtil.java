package com.ng.base;

import java.util.Deque;
import java.util.List;

import android.util.Log;

public class LogUtil {

    public static void d(String str) {
        Log.d("nangua", str);
    }

    public static void pring(ListNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }
        System.out.println(node.toString());
    }

    public static void pring(TreeNode treeNode) {
        treeNode.print();
    }

    public static void pring(List<String> list) {
        System.out.println(list.toString());
    }

    public static void pring(boolean str) {
        System.out.println(str + "");
    }

    public static void pring(String str) {
        System.out.println(str);
    }

    public static void pring(int number) {
        System.out.println(number + "");
    }

    public static void pring(char c) {
        System.out.println(c + "");
    }

    public static void printCharArray(char[] arrays) {
        String result = "";
        for (char temp : arrays) {
            result += " " + String.valueOf(temp);
        }
        System.out.println(result);
    }

    public static void pring(int[] arrays) {
        if (arrays == null) {
            System.out.println("null");
            return;
        }

        String result = "";
        for (int temp : arrays) {
            result += " " + temp;
        }
        System.out.println(result);
    }

    public static void pring(String[] arrays) {
        String result = "";
        for (String temp : arrays) {
            result += " " + temp;
        }
        System.out.println(result);
    }

    public static void pring(int[][] arrays) {
        String result = "";
        for (int[] temp : arrays) {
            result += "{";
            for (int temp2 : temp) {
                result += " " + temp2;
            }
            result += "}\n";
        }
        System.out.println(result);
    }

    public static void pring(char[][] arrays) {
        String result = "";
        for (char[] temp : arrays) {
            result += "{";
            for (char temp2 : temp) {
                result += " " + temp2;
            }
            result += "}\n";
        }
        System.out.println(result);
    }

    public static void pring(char[] arrays) {
        String result = "";
        for (int temp : arrays) {
            result += " " + temp;
        }
        System.out.println(result);
    }

    public static void pring(long value) {
        System.out.println(value + "");

    }

    public static void pring(final double value) {
        System.out.println(value + "");
    }

    public static void pring(Deque<Integer> deque) {
        System.out.println(deque.toString());
    }
}
