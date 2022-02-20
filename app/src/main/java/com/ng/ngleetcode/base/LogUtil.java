package com.ng.ngleetcode.base;

public class LogUtil {

	public static void pring(ListNode node) {
		String result = " ";
		while (node != null) {
			result += node.val + " ,";
			node = node.next;
		}
		System.out.println(result.substring(0, result.length()-1));
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

	public static void printCharArray(char[] arrays) {
		String result = "";
		for (char temp : arrays) {
			result += " " + String.valueOf(temp);
		}
		System.out.println(result);
	}

	public static void pring(int[] arrays) {
		if(arrays == null) {
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

	public static void pring(char[] arrays) {
		String result = "";
		for (int temp : arrays) {
			result += " " + temp;
		}
		System.out.println(result);
	}
}
