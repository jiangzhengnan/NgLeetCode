package com.ng.code.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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

    public static void copyDir(String sourcePath, String newPath) {
        try {
            File file = new File(sourcePath);
            String[] filePath = file.list();

            File newPathFile = new File(newPath);
            if (!newPathFile.exists()) {
                newPathFile.mkdir();
            }

            for (int i = 0; i < filePath.length; i++) {
                if ((new File(sourcePath + File.separator + filePath[i])).isDirectory()) {
                    copyDir(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
                }

                if (new File(sourcePath + File.separator + filePath[i]).isFile()) {
                    copyFile(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
                }

            }
        } catch (Exception e) {
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

    public static void deleteFile(File dir) {
        File[] listFiles = dir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            File temp = listFiles[i];
            if (temp.isDirectory() && !temp.getName().contains("highlightjs")) {
                deleteFile(temp);
            }
            temp.delete();
        }
    }

    public static String getProjectPath() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("local.properties"); // 根据项目结构修改路径
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty("projectpath") + "/";
    }
}
