package com.ng.ngleetcode.http

import com.ng.ngleetcode.http.tree.TreeService

object ApiRepo{

  /**
   * 获取体系列表
   */
  fun getHttpService(): TreeService = RetrofitManager.getApiService(TreeService::class.java)

}