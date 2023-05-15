package com.ng.ngleetcode.model.tree.http.http

import com.ng.ngleetcode.model.tree.bean.ArticleBean
import com.ng.ngleetcode.model.tree.bean.TreeBean
import retrofit2.http.*

/**
 * @date 2020/5/9
 * 
 */
interface ApiService {


    /**
     * 体系
     */
    @GET("/tree/json")
    suspend fun getSystemList() : ApiResponse<MutableList<TreeBean>>


    /**
     * 获取体系文章列表
     */
    @GET("/article/list/{pageNum}/json")
    suspend fun getSystemArticle(@Path("pageNum")pageNum:Int,@Query("cid")cid:Int)
            : ApiResponse<ArticleBean>

}