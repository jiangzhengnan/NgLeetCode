package com.ng.ngleetcode.model.tree.http.http

import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ng.ngleetcode.app.MyApplication
import com.ng.ngleetcode.constants.ApiConstants
import com.ng.ngleetcode.constants.Constants
import com.ng.ngleetcode.model.tree.http.HttpLoggingInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * des Retrofit工厂类
 * 
 * @date 2020-05-09
 */
object RetrofitFactory {
    //缓存100Mb
    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
                .readTimeout(
                    Constants.DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .connectTimeout(
                    Constants.DEFAULT_TIMEOUT.toLong(),
                    TimeUnit.MILLISECONDS
                )
                .addInterceptor(getLogInterceptor())
                .cookieJar(getCookie())
                .cache(getCache())
        }

    fun factory(): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.BASE_URL)
            .build()
    }

    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor():Interceptor{
        //http log 拦截器
        return HttpLoggingInterceptor("OkHttp").apply {
                setPrintLevel(HttpLoggingInterceptor.Level.BODY)
                setColorLevel(Level.INFO)
            }
    }

    /**
     * 获取cookie持久化
     */
    private fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(MyApplication.instance)
        )
    }

    /**
     * 获取缓存方式
     */
    private fun getCache():Cache{
        //缓存100Mb
        return Cache( File(MyApplication.instance.cacheDir, "cache")
            , 1024 * 1024 * 100)
    }
}