package com.ng.code.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author : 
 * @creation : 2022/03/21
 * @description :
 */
public class SharedPreferencesUtils {

    public static final String SP_NAME = "config";
    private static SharedPreferences sp;

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(key, value).apply();
    }

    /**
     * 返回字符串
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(key, defValue);
    }

    /**
     * 保存布尔
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 返回布尔
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        return sp.getBoolean(key, defValue);
    }

    /**
     * 保存int
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        sp.edit().putInt(key, value).apply();
    }

    /**
     * 返回int
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        return sp.getInt(key, defValue);
    }

    /**
     * 保存float
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveFloat(Context context, String key, float value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        sp.edit().putFloat(key, value).apply();
    }

    /**
     * 返回float
     *
     * @param context
     * @param key
     * @param defValue 默认值
     * @return
     */
    public static float getFloat(Context context, String key, float defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }

        return sp.getFloat(key, defValue);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.contains(key);
    }

}
