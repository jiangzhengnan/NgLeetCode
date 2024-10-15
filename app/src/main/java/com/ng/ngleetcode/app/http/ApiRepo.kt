package com.ng.ngleetcode.app.http

import com.ng.ngleetcode.app.http.tree.TreeService

object ApiRepo{

  /**
   * 获取体系列表
   */
  fun getHttpService(): TreeService = RetrofitManager.getApiService(TreeService::class.java)

}