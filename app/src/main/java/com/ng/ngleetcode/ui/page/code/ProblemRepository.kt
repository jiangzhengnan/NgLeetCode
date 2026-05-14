package com.ng.ngleetcode.ui.page.code

import android.content.Context
import com.ng.ngleetcode.BuildConfig
import java.io.File
import java.io.IOException
import java.util.zip.ZipFile

/**
 * @author :
 * @creation : 2022/02/27
 * @description :
 * 数据来源
 *
 * 题库由打包阶段生成的单文件 assets/problems.zip 提供（zip 内目录结构为 "<分类>/<题目>.java"）。
 * 启动时按 versionCode 缓存，把 zip 复制到 App 私有目录后通过 ZipFile 随机访问，
 * 避免之前在 assets 下散文件导致全工程搜索出现两份命中的问题。
 */
object ProblemRepository {

    private const val ASSET_ZIP_NAME = "problems.zip"
    private const val LOCAL_ZIP_NAME = "problems.zip"
    private const val VERSION_TAG_NAME = "problems.zip.version"

    @Volatile
    private var zipFileRef: ZipFile? = null

    /**
     * 获取（必要时初始化）本地 ZipFile 句柄。
     * 缓存策略：用 BuildConfig.VERSION_CODE 做 tag 文件，版本变更时重新从 assets 拷贝。
     */
    @Synchronized
    private fun ensureZipFile(context: Context): ZipFile? {
        zipFileRef?.let { return it }
        try {
            val baseDir = context.filesDir
            val zipTarget = File(baseDir, LOCAL_ZIP_NAME)
            val versionTag = File(baseDir, VERSION_TAG_NAME)
            val currentVersion = BuildConfig.VERSION_CODE.toString()

            val cached = zipTarget.exists() &&
                versionTag.exists() &&
                versionTag.readText() == currentVersion

            if (!cached) {
                context.assets.open(ASSET_ZIP_NAME).use { input ->
                    zipTarget.outputStream().use { output -> input.copyTo(output) }
                }
                versionTag.writeText(currentVersion)
            }
            return ZipFile(zipTarget).also { zipFileRef = it }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * 列出题库中所有 .java 题目相对路径（与改造前 AssetManager.list 风格一致：
     * 形如 "链表/Ⅰ_链表反转.java"）。
     */
    fun getAssetsJavaCodeList(context: Context): List<String> {
        val zip = ensureZipFile(context) ?: return emptyList()
        val result = ArrayList<String>()
        val entries = zip.entries()
        while (entries.hasMoreElements()) {
            val e = entries.nextElement()
            if (!e.isDirectory && e.name.endsWith(".java")) {
                result.add(e.name)
            }
        }
        return result
    }

    /**
     * 读取题目文本数据。
     *
     * @param context  程序上下文
     * @param fileName zip 内 entry 名（如 "链表/Ⅰ_链表反转.java"），与原 assets 相对路径保持一致
     * @return 文件内容；失败返回 null
     */
    fun readAssets(context: Context?, fileName: String?): String? {
        if (context == null || fileName.isNullOrEmpty()) return null
        val zip = ensureZipFile(context) ?: return null
        val entry = zip.getEntry(fileName) ?: return null
        return try {
            zip.getInputStream(entry).bufferedReader(Charsets.UTF_8).use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}