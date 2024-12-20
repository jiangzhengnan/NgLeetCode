package com.ng.code.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.ng.base.utils.LogUtil;

/**
 * @author : 
 * @creation : 2022/02/27
 * @description :
 */
public class ProblemUtil {

    /**
     * 抽题机
     * <p>
     * level: 1 2 3 三个难度
     */
    public static void getRandomTest(int num, int level) {
        LogUtil.print("抽取 " + num + " 道题目");
        Set<Class<?>> codeSet = getClassSet(Constants.CODE_PKG_NAME);
        ArrayList<Class<?>> list = new ArrayList(codeSet);
        List<Integer> repeatList = new ArrayList<>();

        int result = 0;
        while (result < num) {
            int randomIndex = new Random().nextInt(list.size());
            while (repeatList.contains(randomIndex)) {
                randomIndex = new Random().nextInt(list.size());
            }
            repeatList.add(randomIndex);
            Class<?> randomItem = list.get(randomIndex);

            boolean fitLevel = false;
            switch (level) {
                case 1:
                    fitLevel = randomItem.getSimpleName().startsWith("Ⅰ_");
                    break;
                case 2:
                    fitLevel = randomItem.getSimpleName().startsWith("Ⅱ_");
                    break;
                case 3:
                    fitLevel = randomItem.getSimpleName().startsWith("Ⅲ_");
                    break;
                default:
                    continue;
            }

            if (fitLevel) {
                Annotation[] annos = randomItem.getAnnotations();
                for (Annotation anno : annos) {
                    Solution solution = (Solution) anno;
                    if (solution.particle() == 0) {
                        LogUtil.print(
                            (result + 1) + " " + randomItem.getSimpleName() + " easy:" +
                                solution.easy() + " particle:" + solution.particle());
                        result++;
                    }
                }
            }
        }
    }

    //刷新本地目录，补充题库
    public static void refreshLocalProjects() {
        ProgressUtil.getNowProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //输出目录到readme
                //ProgressUtil.refreshReadMe();
                //输出所有题目到assets
                ProblemUtil.copyJavaToAssets();
            }
        }).start();
    }

    public static void copyJavaToAssets() {
        File newPathFile = new File(Constants.ASSETS_DIR_PATH);
        FileUtil.deleteFile(newPathFile);
        FileUtil.copyDir(Constants.JAVA_DIR_PATH,Constants.ASSETS_DIR_PATH);
    }

    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        addClass(classSet, packagePath, packageName);
                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
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
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        if (!className.contains("$")) {
            classSet.add(cls);
        }
    }

    private static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }

}
