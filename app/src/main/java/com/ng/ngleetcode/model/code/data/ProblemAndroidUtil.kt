package com.ng.ngleetcode.model.code.data

import android.content.Context
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

/**
 * @author :
 * @creation : 2022/02/27
 * @description :
 */
object ProblemAndroidUtil {

    fun getAssetsJavaCodeList(context: Context): List<String> {
        val result: MutableList<String> = ArrayList()
        try {
            val files = context.assets.list("")
            for (temp in files!!) {
                val subFiles = context.assets.list(temp)
                for (subTemp in subFiles!!) {
                    val realFilePath = "$temp/$subTemp"
                    if (realFilePath.endsWith(".java")) {
                        result.add(realFilePath)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 读取文本数据
     *
     * @param context  程序上下文
     * @param fileName 文件名
     * @return String, 读取到的文本内容，失败返回null
     */
    fun readAssets(context: Context?, fileName: String?): String? {
        var ins: InputStream? = null
        var content: String? = null
        try {
            ins = context?.assets?.open(fileName!!)
            if (ins != null) {
                val buffer = ByteArray(1024)
                val arrayOutputStream = ByteArrayOutputStream()
                while (true) {
                    val readLength = ins.read(buffer)
                    if (readLength == -1) {
                        break
                    }
                    arrayOutputStream.write(buffer, 0, readLength)
                }
                ins.close()
                arrayOutputStream.close()
                content = String(arrayOutputStream.toByteArray())
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
            content = null
        } finally {
            try {
                ins?.close()
            } catch (ioe: IOException) {
                ioe.printStackTrace()
            }
        }
        return content
    }
}