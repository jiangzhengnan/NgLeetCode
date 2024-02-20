package com.ng.base.utils;

import java.util.Deque;
import java.util.List;

public class LogUtil {

    public static void print(ListNode node) {
        if (node == null) {
            System.out.println("null");
            return;
        }
        System.out.println(node.toString());
    }

    public static void print(TreeNode treeNode) {
        treeNode.print();
    }

    public static void print(boolean str) {
        System.out.println(str + "");
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void print(int number) {
        System.out.println(number + "");
    }

    public static void print(char c) {
        System.out.println(c + "");
    }

    public static void printCharArray(char[] arrays) {
        String result = "";
        for (char temp : arrays) {
            result += " " + String.valueOf(temp);
        }
        System.out.println(result);
    }

    public static void print(int[] arrays) {
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

    public static void print(String[] arrays) {
        String result = "";
        for (String temp : arrays) {
            result += " " + temp;
        }
        System.out.println(result);
    }

    public static void print(int[][] arrays) {
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

    public static void print(char[][] arrays) {
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

    public static void print(char[] arrays) {
        String result = "";
        for (int temp : arrays) {
            result += " " + temp;
        }
        System.out.println(result);
    }

    public static void print(long value) {
        System.out.println(value + "");
    }

    public static void print(List<String> value) {
        System.out.println(value.toString());
    }

    public static void print(final double value) {
        System.out.println(value + "");
    }

    public static void print(Deque<Integer> deque) {
        System.out.println(deque.toString());
    }

    public interface Function {
        void fun();
    }

    public static void calculateTime(String tag, int times, Function function) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            function.fun();
        }
        print(tag + " 执行耗时:" + (System.currentTimeMillis() - time));

    }
}
