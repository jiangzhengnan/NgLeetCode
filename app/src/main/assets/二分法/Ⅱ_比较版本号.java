package com.ng.code.menu.二分法;

import com.ng.code.util.LogUtil;
import com.ng.code.util.Solution;

/**
 * 日期:
 * 原题链接:https://www.nowcoder.com/practice/2b317e02f14247a49ffdbdba315459e7?tpId=295&tqId=1024572&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Ftab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * 原题描述:
 * 输入：
 * "1.1","2.1"
 * 返回值：
 * -1
 * 说明：
 * version1 中下标为 0 的修订号是 "1"，version2 中下标为 0 的修订号是 "2" 。1 < 2，所以 version1 < version2，返回-1
 */
@Solution(easy = 0, hard = 0, partice = 0)
public class Ⅱ_比较版本号 {

    public static void main(String[] args) {

        LogUtil.pring(EasySolution.compare("123.7000000000","132.6000000000"));
        //LogUtil.pring(HardSolution.compare("0521258910015366891536610439570192261909342070503523734290312191621191022379164580.2333299489986811254536505526685287049940.875967076280887488166841.51243730182764791883163159785117673849461045352926902339869521468483909803860975050.0293529543181626696650219181356232739114256270097521489794008255711492936898286685469519181786333223", "0521258910015366891536610439570192261909342070503523734290312191621191022379164580.2333299489986811254536505526685287049940.875967076280887488166841.51243730182764791883163159785117673849461045352926902339869521468483909803860975050.293529543181626696650219181356232739114256270097521489794008255711492936898286685469519181786333223"));
    }

    /**
     * 每一位比较，如果少了就当0处理
     * 如果将字符串转int类型，会超出int的范围，所以直接使用字符串比较ascii码。遇到开头数字为0的，去掉前缀0，如果全为0的直接截取最后一位即可。
     */
    private static class EasySolution {

        public static int compare(String version1, String version2) {
            // write code here
            String[] ver1 = version1.split("\\.");
            String[] ver2 = version2.split("\\.");
            int max = Math.max(ver1.length, ver2.length);
            for (int i = 0; i < max; i++) {
                String a = i < ver1.length ? ver1[i] : "0";
                String b = i < ver2.length ? ver2[i] : "0";
                for (int j = 0; j < a.length(); j++) {
                    if (j == a.length() - 1 || a.charAt(j) != '0') {
                        a = a.substring(j);
                        break;
                    }
                }
                for (int j = 0; j < b.length(); j++) {
                    if (j == b.length() - 1 || b.charAt(j) != '0') {
                        b = b.substring(j);
                        break;
                    }
                }
                if (a.length() > b.length()) {
                    return 1;
                } else if (a.length() < b.length()) {
                    return -1;
                } else {
                    if (a.compareTo(b) > 0) {
                        return 1;
                    } else if (a.compareTo(b) < 0) {
                        return -1;
                    }
                }
            }
            return 0;
        }

    }

    private static class HardSolution {


    }

}
