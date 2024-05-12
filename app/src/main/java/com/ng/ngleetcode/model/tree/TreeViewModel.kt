package com.ng.ngleetcode.model.tree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ng.base.BaseViewModel
import com.ng.ngleetcode.app.MyApplication
import com.ng.ngleetcode.model.tree.bean.ArticleListBean
import com.ng.ngleetcode.model.tree.bean.TreeBean
import com.ng.ngleetcode.model.tree.http.TreeRepo

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/15
 *    @description   :
 */
class TreeViewModel : BaseViewModel(MyApplication.instance) {


    private val repo by lazy { TreeRepo() }

    /**
     * 体系列表数据
     */
    private val _systemLiveData = MutableLiveData<MutableList<TreeBean>>()
    val systemLiveData: LiveData<MutableList<TreeBean>> = _systemLiveData

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