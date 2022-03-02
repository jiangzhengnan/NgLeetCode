package com.ng.ngleetcode.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static boolean saveContent(String path, String content) {
        try {
            // 动态获得路径
            File file = new File(path);
            // 输出流，把数据输出到文件中
            FileOutputStream fos = new FileOutputStream(file);
            // 写入字节流
            fos.write(content.getBytes());
            // 清空缓存
            fos.flush();
            // 关闭流
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void copyDir(String sourcePath, String newPath)   {
        try {
            File file = new File(sourcePath);
            String[] filePath = file.list();

            if (!(new File(newPath)).exists()) {
                (new File(newPath)).mkdir();
            }

            for (int i = 0; i < filePath.length; i++) {
                if ((new File(sourcePath + File.separator + filePath[i])).isDirectory()) {
                    copyDir(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
                }

                if (new File(sourcePath + File.separator + filePath[i]).isFile()) {
                    copyFile(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);
        ;

        byte[] buffer = new byte[2097152];
        int readByte = 0;
        while ((readByte = in.read(buffer)) != -1) {
            out.write(buffer, 0, readByte);
        }

        in.close();
        out.close();
    }
}
