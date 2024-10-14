package com.ng.ngleetcode.old.model.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ng.base.BaseViewModel
import com.ng.ngleetcode.app.MyApp
import com.ng.ngleetcode.old.model.tree.bean.ArticleListBean
import com.ng.ngleetcode.old.model.tree.http.http.TreeRepo

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/15
 *    @description   :
 */
class TreeViewModel : BaseViewModel(MyApp.instance) {


    private val repo by lazy { TreeRepo() }

    /**
     * 体系列表数据
     */
    private val _systemLiveData = MutableLiveData<MutableList<com.ng.ngleetcode.old.model.tree.bean.TreeBean>>()
    val systemLiveData: LiveData<MutableList<com.ng.ngleetcode.old.model.tree.bean.TreeBean>> = _systemLiveData

    /**
     * 体系列表数据
     */
    private val _articleLiveData = MutableLiveData<MutableList<ArticleListBean>>()
    val articleLiveData: LiveData<MutableList<ArticleListBean>> = _articleLiveData

    /**
     * 获取体系列表
     */
    fun getSystemList() {
        launchUI {
            _systemLiveData.value = repo.getSystemList()
        }
    }

    /**
     * 获取文章列表
     */
    fun getArticleList(id: Int) {
        launchUI {
            _articleLiveData.value = repo.getArticleList(id)
            handleList(_articleLiveData)
        }
    }

    /**
     * 获取文章列表
     */
    fun loadMoreArticleList(id: Int) {
        launchUI {
            val list = _articleLiveData.value
            list?.addAll(repo.loadMoreArticle(id))
            _articleLiveData.value = list!!
            handleList(_articleLiveData)
        }
    }


}