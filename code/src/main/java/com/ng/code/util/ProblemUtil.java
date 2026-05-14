package com.ng.code.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
     * <p>
     * 异步执行：内部启动一个子线程执行扫题/抽题，方法立即返回，
     * 这样可以与 {@link #refreshLocalProjects()} 中的 copyJavaToAssets 并发，加速启动。
     */
    public static void getRandomTest(final int num, final int level) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doGetRandomTest(num, level);
            }
        }, "ProblemUtil-getRandomTest");
        thread.start();
    }

    private static void doGetRandomTest(int num, int level) {
        LogUtil.print("抽取 " + num + " 道题目");
        Set<Class<?>> codeSet = getClassSet(Constants.CODE_PKG_NAME);
        ArrayList<Class<?>> list = new ArrayList(codeSet);
        if (list.isEmpty()) {
            LogUtil.print("题库为空，未扫到任何题目类，跳过抽题。请检查 Constants.JAVA_DIR_PATH: " + Constants.JAVA_DIR_PATH);
            return;
        }
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
                    if (solution.easy() + solution.hard() == 0) {
                        LogUtil.print(
                            (result + 1) + " " + randomItem.getSimpleName() + " easy:" +
                                solution.easy() + " hard:" + solution.hard());
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

    /**
     * 把 code 模块下的题目源码打包成 assets/problems.zip，供 App 端只读访问。
     * <p>
     * 历史上这里是把每个 .java 都散拷贝到 assets/ 下，导致：
     * 1) 每个题目在工程内有两份，全工程搜索会出现两份命中；
     * 2) APK 体积更大。
     * 现改为单文件 zip：内部仍保留原目录结构（如 "链表/Ⅰ_链表反转.java"），App 端通过 ZipFile 随机访问。
     */
    public static void copyJavaToAssets() {
        File assetsDir = new File(Constants.ASSETS_DIR_PATH);
        if (!assetsDir.exists()) {
            assetsDir.mkdirs();
        }
        // 1) 清理历史散文件（保留 highlightjs 等非题库目录）
        cleanLegacyAssetsExceptKeep(assetsDir);

        // 2) 把题库目录整体压成 problems.zip
        File srcDir = new File(Constants.JAVA_DIR_PATH);
        if (!srcDir.exists() || !srcDir.isDirectory()) {
            System.err.println("[ProblemUtil] copyJavaToAssets 跳过：源目录不存在 -> " + Constants.JAVA_DIR_PATH);
            return;
        }
        File zipFile = new File(assetsDir, Constants.PROBLEMS_ZIP_NAME);
        try {
            zipDirectory(srcDir, zipFile);
            LogUtil.print("[ProblemUtil] 已生成题库压缩包: " + zipFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除 assets 下历史的散落题目文件/目录，保留 highlightjs 这类非题库资源。
     * 只删除目录形态的题目（含 .java），与 problems.zip 自身不受影响（重新生成时会被覆盖）。
     */
    private static void cleanLegacyAssetsExceptKeep(File assetsDir) {
        File[] children = assetsDir.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            String name = child.getName();
            if (child.isDirectory()) {
                if (name.contains("highlightjs")) {
                    continue;
                }
                deleteRecursively(child);
            }
            // 顶层散文件（除 problems.zip 本身）一并清理
            else if (child.isFile() && !name.equals(Constants.PROBLEMS_ZIP_NAME)) {
                child.delete();
            }
        }
    }

    private static void deleteRecursively(File f) {
        if (f == null || !f.exists()) {
            return;
        }
        if (f.isDirectory()) {
            File[] sub = f.listFiles();
            if (sub != null) {
                for (File s : sub) {
                    deleteRecursively(s);
                }
            }
        }
        f.delete();
    }

    /**
     * 将 srcDir 内全部 .java 文件压入 outZip，zip 内 entry 名形如 "链表/Ⅰ_xxx.java"，
     * 与原本散拷贝时 App 看到的相对路径完全一致，App 端无需改 path 拼接。
     */
    private static void zipDirectory(File srcDir, File outZip) throws IOException {
        if (outZip.exists()) {
            outZip.delete();
        }
        try (ZipOutputStream zos = new ZipOutputStream(
            new BufferedOutputStream(new FileOutputStream(outZip)))) {
            // 为了 zip 内 entry 顺序稳定，避免每次生成的 zip 二进制有差异（影响 git diff），
            // 这里收集到 list 中再排序写入。
            List<File> files = new ArrayList<>();
            collectJavaFiles(srcDir, files);
            files.sort((a, b) -> a.getAbsolutePath().compareTo(b.getAbsolutePath()));

            byte[] buf = new byte[8192];
            String basePath = srcDir.getAbsolutePath();
            for (File f : files) {
                String entryName = f.getAbsolutePath().substring(basePath.length() + 1)
                    .replace(File.separatorChar, '/');
                ZipEntry entry = new ZipEntry(entryName);
                // 固定时间戳，进一步降低 zip 二进制波动（同样的源码生成同样的 zip）
                entry.setTime(0L);
                zos.putNextEntry(entry);
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
                    int n;
                    while ((n = bis.read(buf)) != -1) {
                        zos.write(buf, 0, n);
                    }
                }
                zos.closeEntry();
            }
        }
    }

    private static void collectJavaFiles(File dir, List<File> out) {
        File[] children = dir.listFiles();
        if (children == null) {
            return;
        }
        for (File c : children) {
            if (c.isDirectory()) {
                collectJavaFiles(c, out);
            } else if (c.isFile() && c.getName().endsWith(".java")) {
                out.add(c);
            }
        }
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

        // 兜底：当 ClassLoader 扫不到任何类时（常见于 Android module 直接跑普通 main），
        // 改为从源码目录扫描 .java 文件来加载题目类。
        if (classSet.isEmpty() && Constants.CODE_PKG_NAME.equals(packageName)) {
            File srcDir = new File(Constants.JAVA_DIR_PATH);
            if (srcDir.exists() && srcDir.isDirectory()) {
                addClassFromJavaSource(classSet, srcDir, packageName);
            }
        }
        return classSet;
    }

    /**
     * 从源码目录递归扫描 .java 文件，并尝试通过类全限定名加载。
     */
    private static void addClassFromJavaSource(Set<Class<?>> classSet, File dir, String packageName) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                String subPackage = isNotEmpty(packageName) ? packageName + "." + file.getName() : file.getName();
                addClassFromJavaSource(classSet, file, subPackage);
            } else if (file.isFile() && file.getName().endsWith(".java")) {
                String simpleName = file.getName().substring(0, file.getName().length() - ".java".length());
                String className = isNotEmpty(packageName) ? packageName + "." + simpleName : simpleName;
                try {
                    Class<?> cls = Class.forName(className, false, getClassLoader());
                    if (!className.contains("$")) {
                        classSet.add(cls);
                    }
                } catch (Throwable ignore) {
                    // 该类没编译进当前 classpath 时跳过
                }
            }
        }
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        if (files == null) {
            return;
        }
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
