package com.ng.ngleetcode.old.model.tree.http.http

/**
 * 用来封装业务错误信息
 *
 * 
 * @date 2020-05-09
 */
class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()