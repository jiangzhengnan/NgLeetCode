/**
 *****************************************************************************
 * Copyright (C) 2005-2018 UCWEB Corporation. All rights reserved
 * File        : StringUtils
 *
 * Description : StringUtils，字符串工具类集合
 *
 * Creation    : 2018-09-15
 * History     : Creation, 2018-09-15, liuke.zlk, Create the file
 *****************************************************************************
 **/
package com.ng.ngleetcode.train.monitor.utils;


import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;


public class StringUtils {
    public static final String UNKNOWN = "unknown";
    private static Pattern percentagePattern = Pattern.compile("((\\d{1,2})|(100))%");
    private static Pattern absolutePattern = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");

    public static boolean isEmpty(@Nullable String text) {

        return text == null || text.length() == 0;
    }

    public static boolean equals(@Nullable String str1, @Nullable String str2) {
        String str1NoNull = str1 == null ? "" : str1;
        String str2NoNull = str2 == null ? "" : str2;
        return str1NoNull.equals(str2NoNull);
    }

    public static boolean isNotEmpty(@Nullable String text) {
        return !isEmpty(text);
    }

    /**
     * Strip any white space in |input| string.
     *
     * @param input   input string
     * @param replace replace string
     * @return sanitized string
     */
    public static String sanitizeString(String input, String replace) {
        String result = input;
        if (isEmpty(result)) {
            result = replace;
        }

        result = result.replaceAll("\\s", "");
        if (isEmpty(result)) {
            result = replace;
        }

        return result;
    }

    public static String pretectNull(String str){
        return str == null? "" : str;
    }


    public static String[] split(String text, String separator) {
        return split(text,separator,true);
    }

    public static String[] split(String text, String separator, boolean withEmptyString) {
        if (isEmpty(text)) {
            return new String[0];
        }

        if( separator == null || separator.length() == 0){
            return  new String[]{ text };
        }

        String[] sTarget = null;
        int sTargetLength = 0;
        int sLength = text.length();
        int sStartIndex = 0;
        int sEndIndex = 0;

        //扫描字符串，确定目标字符串数组的长度
        for (sEndIndex = text.indexOf(separator, 0); sEndIndex != -1 && sEndIndex < sLength;
             sEndIndex = text.indexOf(separator, sEndIndex)) {
            sTargetLength += (withEmptyString || sStartIndex != sEndIndex) ? 1 : 0;
            sStartIndex = sEndIndex += separator.length();
        }

        //如果最后一个标记的位置非字符串的结尾，则需要处理结束串
        sTargetLength += withEmptyString || sStartIndex != sLength ? 1 : 0;

        //重置变量值，根据标记拆分字符串
        sTarget = new String[sTargetLength];
        int sIndex = 0;
        for (sIndex = 0, sEndIndex = text.indexOf(separator, 0), sStartIndex = 0;
             sEndIndex != -1 && sEndIndex < sLength;
             sEndIndex = text.indexOf(separator, sEndIndex)) {
            if (withEmptyString || sStartIndex != sEndIndex) {
                sTarget[sIndex] = text.substring(sStartIndex, sEndIndex);
                ++sIndex;
            }
            sStartIndex = sEndIndex += separator.length();
        }

        //取结束的子串
        if (withEmptyString || sStartIndex != sLength) {
            sTarget[sTargetLength - 1] = text.substring(sStartIndex);
        }

        return sTarget;
    }

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * Split string and make sure the result is predictable.
     * <p/>
     * <p>
     * For example, String.split() will return ["","","a"] for "``a", but ["a"]
     * for "a``" which is unpredictable.
     * </p>
     *
     * @param splitter
     *   currently doesn't support regular expression
     * @param keepEmpty
     *   keep empty
     * @param purgeSpaceIsEmpty
     *   purge space
     *
     * @return string array
     */
    public static String[] split(String src,
                                 String splitter,
                                 boolean keepEmpty,
                                 boolean purgeSpaceIsEmpty) {
        if (isEmpty(src)) {
            return EMPTY_STRING_ARRAY;
        }
        final int srcLen = src.length();

        int index;
        int lastIndex = 0;
        String subStr;
        ArrayList<String> tmpResult = new ArrayList<>();

        while (lastIndex <= srcLen) {
            // when lastIndex == srcLen, no exception, index = -1
            index = src.indexOf(splitter, lastIndex);
            if (index < 0) {
                index = srcLen;
            }
            subStr = src.substring(lastIndex, index);
            lastIndex = index + 1;
            if (purgeSpaceIsEmpty && isEmpty(subStr)) {
                subStr = "";
            }
            if (keepEmpty || subStr.length() > 0) {
                tmpResult.add(subStr);
            }
        }

        String[] result = new String[tmpResult.size()];
        tmpResult.toArray(result);
        return result;
    }

    /**
     *
     * <p>使用给定的 replacement 替换此字符串所有匹配给定的匹配的子字符串</p>
     *
     * <b>修改历史</b>
     * <ol>
     * <li>创建（Added by luogw on 2011-11-16）</li>
     * </ol>
     * @param srcString 源字符串
     * @param matchingString 匹配的字符串
     * @param replacement 替换的字符串
     * @param ignoreCase 是否不关注大小写
     * @return 返回替换操作后的字符串
     */
    public static String replaceAll(String srcString, String matchingString, String replacement, boolean ignoreCase) {
        if (isEmpty(srcString) || isEmpty(matchingString) || isEmpty(replacement)) {
            return "";
        }

        StringBuilder sResult = new StringBuilder();
        int sIndex = 0;
        int sMaxIndex = srcString.length() - 1;
        while ((sIndex = index(srcString, matchingString, ignoreCase)) != -1) {
            String sPreStr = srcString.substring(0, sIndex);
            sResult.append(sPreStr).append(replacement);
            srcString = (sIndex < sMaxIndex) ? srcString.substring(sIndex + matchingString.length()) : "";
        }
        sResult.append(srcString);

        return sResult.toString();
    }


    public static String byteToHexString(byte[] input) {
        if (input == null) {
            return "";
        }
        String output = "";
        String tmp;
        for (byte b : input) {
            tmp = Integer.toHexString(b & 0xFF);
            if (tmp.length() == 1) {
                output = output + "0" + tmp;
            } else {
                output = output + tmp;
            }
        }
        return output.toLowerCase(Locale.ENGLISH);
    }

    /**
     * from https://stackoverflow.com/questions/1126227/indexof-case-sensitive
     */
    public static int index(@NonNull final String src, @NonNull final String sub, boolean ignoreCase) {
        if (isEmpty(sub) || isEmpty(src) || !ignoreCase) {
            // Fallback to legacy behavior.
            return src.indexOf(sub);
        }

        int srcLen = src.length();
        int subLen = sub.length();
        for (int i = 0; i < srcLen; ++i) {
            // Early out, if possible.
            if (i + subLen > srcLen) {
                return -1;
            }

            // Attempt to match substring starting at position i of haystack.
            int j = 0;
            int ii = i;
            while (ii < srcLen && j < subLen) {
                char c = Character.toLowerCase(src.charAt(ii));
                char c2 = Character.toLowerCase(sub.charAt(j));
                if (c != c2) {
                    break;
                }
                j++;
                ii++;
            }
            // Walked all the way to the end of the needle, return the start
            // position that this was found.
            if (j == subLen) {
                return i;
            }
        }

        return -1;
    }



    public static long parseLong(String num, long def) {
        if (isEmpty(num)) {
            return def;
        }
        try {
            return Long.parseLong(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    public static int parseInt(String num, int def) {
        if (isEmpty(num)) {
            return def;
        }
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    public static double parseDouble(String num, double def) {
        if (isEmpty(num)) {
            return def;
        }
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return def;
    }

    public static boolean isPercentageTracker(String progressValue) {
        return !TextUtils.isEmpty(progressValue)
               && percentagePattern.matcher(progressValue).matches();
    }

    public static boolean isAbsoluteTracker(String progressValue) {
        return !TextUtils.isEmpty(progressValue)
               && absolutePattern.matcher(progressValue).matches();
    }

    /**
     * HH:MM:SS.mmm
     * .mmm是可选,
     * 这里单位转为ms
     */
    public static int parseAbsoluteOffset(String progressValue) {
        if (progressValue == null) {
            return -1;
        }

        final String[] split = progressValue.split(":");
        if (split.length != 3) {
            return -1;
        }

        try {
            return Integer.parseInt(split[0]) * 60 * 60 * 1000 // Hours
                   + Integer.parseInt(split[1]) * 60 * 1000    // Minutes
                   + (int) (Float.parseFloat(split[2]) * 1000);// SS.mmm，所以用float
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String transferredWaString(String origin){
        String result = "";
        if (isNotEmpty(origin)) {
            result = origin.replaceAll("=", " ").replaceAll("`", " ").replaceAll("\n", "");
        }
        return result;
    }

    public static void throwCatchEx(String msg){
        throw new RuntimeException(msg);
    }

}