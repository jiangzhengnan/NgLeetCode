package com.ng.code.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
            if (filePath == null) {
                System.err.println("[FileUtil] copyDir 跳过：源目录不存在或不可读 -> " + sourcePath);
                return;
            }

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
        if (listFiles == null) {
            return;
        }
        for (int i = 0; i < listFiles.length; i++) {
            File temp = listFiles[i];
            if (temp.isDirectory() && !temp.getName().contains("highlightjs")) {
                deleteFile(temp);
            }
            temp.delete();
        }
    }

    public static String getProjectPath() {
        // 起点：JVM 工作目录（user.dir 是原生字符串，不会被 URL 编码污染）
        String userDir = System.getProperty("user.dir");
        File start = userDir == null ? new File("").getAbsoluteFile() : new File(userDir);

        // 1) 沿工作目录及其祖先目录依次查找 local.properties，优先读其中的 projectpath
        File cur = start;
        while (cur != null) {
            File lp = new File(cur, "local.properties");
            if (lp.exists()) {
                String fromLp = readProjectPathFromLocalProperties(lp);
                if (fromLp != null && !fromLp.isEmpty()) {
                    return fromLp + "/";
                }
                // 找到了 local.properties 但里面没配 projectpath，那就把它所在目录视为工程根
                return cur.getAbsolutePath() + "/";
            }
            cur = cur.getParentFile();
        }

        // 2) 兜底：沿祖先目录找含有 code/src/main/java 子目录的目录作为工程根
        cur = start;
        while (cur != null) {
            if (new File(cur, "code/src/main/java").isDirectory()) {
                System.err.println("[FileUtil] 未找到 local.properties，使用自动探测的工程根目录: " + cur.getAbsolutePath());
                return cur.getAbsolutePath() + "/";
            }
            cur = cur.getParentFile();
        }

        // 3) 最终兜底：当前工作目录
        String fallback = start.getAbsolutePath();
        System.err.println("[FileUtil] 工程根目录探测失败，使用当前工作目录: " + fallback);
        return fallback + "/";
    }

    private static String readProjectPathFromLocalProperties(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        Properties properties = new Properties();
        // 注意：Properties.load(InputStream) 默认使用 ISO-8859-1 解码，
        // 在中文路径下会把 UTF-8 字节解读成乱码（如 "个人" -> "ä¸ªäºº"）。
        // 这里改用 Reader + UTF-8 读取，确保中文路径正确。
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.getProperty("projectpath");
    }
}
