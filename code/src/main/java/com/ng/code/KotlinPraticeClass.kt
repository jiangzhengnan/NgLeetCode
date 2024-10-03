package com.ng.code

import com.ng.base.utils.LogUtil
import kotlinx.coroutines.*

class KotlinPraticeClass {

  /**
   * 2.多异步请求连续调用
   */
  fun test2() {
    LogUtil.print("test2 start")
    MainScope().launch {
      val uploadUrl = requestUploaderUrl()

      val uploadResult = uploadHeadImg(uploadUrl)
      if (uploadResult) {
        LogUtil.print("刷新ui")
      }
    }
    LogUtil.print("test2 end")
  }

  //请求头像上传地址
  private suspend fun requestUploaderUrl(): String {
    return withContext(Dispatchers.IO) {
      delay(2000)
      val url = "http://xxx.com"
      LogUtil.print("请求头像上传地址:$url")
      return@withContext url
    }
  }

  //上传头像
  private suspend fun uploadHeadImg(url: String): Boolean {
    return withContext(Dispatchers.IO) {
      delay(2000)
      val resp = true
      LogUtil.print("上传头像结果:$resp")
      return@withContext resp
    }
  }
}

fun main() {
  val test :KotlinPraticeClass  = KotlinPraticeClass()
  test.test2()

}
 