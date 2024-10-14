package com.ng.ngleetcode.http

import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.ng.ngleetcode.MyApp
import com.ng.ngleetcode.http.interceptor.CacheCookieInterceptor
import com.ng.ngleetcode.old.constants.ApiConstants
import com.ng.ngleetcode.old.constants.Constants
import com.zj.wanandroid.data.http.interceptor.SetCookieInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

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
                .addInterceptor(SetCookieInterceptor())
                .addInterceptor(CacheCookieInterceptor())
                .cookieJar(getCookie())
                //不验证证书
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(TrustAllNameVerifier())
                .cache(getCache())
        }


    private fun createSSLSocketFactory(): SSLSocketFactory {
        lateinit var ssfFactory: SSLSocketFactory
        try {
            val sslFactory = SSLContext.getInstance("TLS")
            sslFactory.init(null,  arrayOf(TrustAllCerts()), SecureRandom());
            ssfFactory = sslFactory.socketFactory
        } catch (e: Exception) {
            print("SSL错误：${e.message}")
        }
        return ssfFactory
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
        return com.ng.ngleetcode.old.model.tree.http.HttpLoggingInterceptor("OkHttp").apply {
                setPrintLevel(com.ng.ngleetcode.old.model.tree.http.HttpLoggingInterceptor.Level.BODY)
                setColorLevel(Level.INFO)
            }
    }

    /**
     * 获取cookie持久化
     */
    private fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(MyApp.instance)
        )
    }

    /**
     * 获取缓存方式
     */
    private fun getCache():Cache{
        //缓存100Mb
        return Cache(
            File(MyApp.instance.cacheDir, "cache"), 1024 * 1024 * 100
        )
    }
}