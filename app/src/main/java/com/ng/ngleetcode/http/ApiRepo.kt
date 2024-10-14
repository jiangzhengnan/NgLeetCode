package com.ng.ngleetcode.http

object ApiRepo{

  /**
   * 获取体系列表
   */
  fun getHttpService(): HttpService = RetrofitManager.getApiService(HttpService::class.java)

}