package com.ng.ngbaselib.network.intercepter

import okhttp3.Interceptor
import okhttp3.Response

open class BaseInterceptor(private val headers: Map<String, String>?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().run {
            if (!headers.isNullOrEmpty()) {
                for (headMap in headers) {
                    addHeader(headMap.key, headMap.value).build()
                }
            }
            build()
        })
    }
}