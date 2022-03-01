package com.ng.ngleetcode.util;

import android.content.Context;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/02/27
 * @description :
 * 进度统计
 */
public class ProgressUtil {

    public static void getNowProgress() {
        Set<Class<?>> codeSet = ProblemUtil.getClassSet(Constants.CODE_PKG_NAME);
        ArrayList<Class<?>> list = new ArrayList(codeSet);

        int easy = 0, hard = 0;
        for (Class clazz : list) {
            // 获取类上的注解
            Annotation[] annos = clazz.getAnnotations();
            for (Annotation anno : annos) {
                Solution solution = (Solution) anno;
                if (solution.easy() > 0) {
                    easy++;
                }
                if (solution.hard() > 0) {
                    hard++;
                }
            }
        }
        LogUtil.pring("题库总数: " + list.size() + " 简单完成: " + easy + " 困难完成: " + easy);
    }

    /**
     * 刷新read me
     * 类似格式
     * ## Java 知识点汇总
     * * [JVM](https://github.com/JasonWu1111/Android-Review/blob/master/Docs/Java知识点汇总.md#jvm)
     * * [JVM 工作流程](https://github.com/JasonWu1111/Android-Review/blob/master/Docs/Java知识点汇总.md#jvm-工作流程)
     * * [运行时数据区（Runtime Data Area）](https://github.com/JasonWu1111/Android-Review/blob/master/Docs/Java知识点汇总.md#运行时数据区runtime-data-area)
     * * [方法指令](https://github.com/JasonWu1111/Android-Review/blob/master/Docs/Java知识点汇总.md#方法指令)
     */
    public static void refreshReadMe() {
        String path = "/Users/xiaoguagua/AndroidProjects/MyProjects/NgLeetCode";
        StringBuilder directorySb = new StringBuilder();

        HashMap<String, List<MyCodeProblem>> classMap = getClassMap(Constants.CODE_PKG_NAME);
        directorySb.append("# 题库");
        for (String key : classMap.keySet()) {
            List<MyCodeProblem> problems = classMap.get(key);
            directorySb.append("## ").append(key).append("\n");
            if (problems != null) {
                for (MyCodeProblem problem : problems) {
                    directorySb.append("* [").append(problem.name).append("](").append(problem.link).append(")\n");
                }
            }
        }
        saveContent(path, directorySb.toString());
    }


    public static boolean saveContent(String path, String content) {
        try {
            // 动态获得路径
            File file = new File(path, "README.md");
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

    public static HashMap<String, List<MyCodeProblem>> getClassMap(String packageName) {
        HashMap<String, List<MyCodeProblem>> classMap = new HashMap<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classMap, packagePath, packageName);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classMap;
    }

    private static void addClass(HashMap<String, List<MyCodeProblem>> classMap, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (isNotEmpty(packageName)) {
                    className = packageName + "|" + className;
                }
                doAddClass(classMap, className);

            } else {
                String subPackagePath = fileName;
                if (isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (isNotEmpty(packageName)) {
                    subPackageName = packageName + "|" + subPackageName;
                }
                addClass(classMap, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(HashMap<String, List<MyCodeProblem>> classMap, String className) {
        if (!className.contains("$")) {
            className = className.replace(Constants.CODE_PKG_NAME, "");
            String[] classSplit = className.split("\\|");
            String pkgName = "";
            String realClassName = "";
            if (classSplit.length == 2) {
                pkgName = Constants.DEFAULT_PKG;
                realClassName = classSplit[1];
            } else if (classSplit.length == 3) {
                pkgName = classSplit[1];
                realClassName = classSplit[2];
            }

            List<MyCodeProblem> classList = new ArrayList<>();
            if (classMap.containsKey(pkgName)) {
                classList = classMap.get(pkgName);
            }
            if (classList != null) {
                String link;
                if (Constants.DEFAULT_PKG.equals(pkgName)) {
                    link = Constants.GITHUB_LINK_HEAD + realClassName + ".java";
                } else {
                    link = Constants.GITHUB_LINK_HEAD + pkgName + "/" + realClassName + ".java";
                }
                classList.add(new MyCodeProblem(realClassName, pkgName, link));
                classMap.put(pkgName, classList);
            }

        }
    }

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }
}
