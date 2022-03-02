package com.ng.ngleetcode.util;

import android.content.Context;
import android.util.Log;

import com.ng.ngleetcode.android.CodeBean;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/02/27
 * @description :
 */
public class ProblemAndroidUtil {

    /**
     * 从assets中随机抽取一道题
     */
    public static CodeBean getRandomProblemJavaContent(Context context) {
        List<String> codeList = getAssetsJavaCodeList(context);
        int randNum = new Random().nextInt(codeList.size() -1);
        String content = readAssets(context, codeList.get(randNum));
        return new CodeBean(codeList.get(randNum),content);
    }

    public static List<String> getAssetsJavaCodeList(Context context) {
        List<String> result = new ArrayList<>();
        try {
            String[] files = context.getAssets().list("");
            for (String temp : files) {
                String[] subFiles = context.getAssets().list(temp);
                for (String subTemp : subFiles) {
                    String realFilePath = temp + "/" + subTemp;
                    if (realFilePath.endsWith(".java")) {
                        result.add(realFilePath);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static void doAddClass(List<String> result, String className) {
        Log.d("nangua", "传进来的:" + className);
    }

    /**
     * 读取文本数据
     *
     * @param context  程序上下文
     * @param fileName 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1)
                        break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }
}
