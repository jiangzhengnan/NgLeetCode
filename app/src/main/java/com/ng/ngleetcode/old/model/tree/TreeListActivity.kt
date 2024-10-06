package com.ng.ngleetcode.old.model.tree

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.ng.base.BaseActivity
import com.ng.base.ext.smartConfig
import com.ng.base.ext.smartDismiss
import com.ng.base.utils.MLog
import com.ng.base.utils.Param
import com.ng.ngleetcode.databinding.ActivityTreeListBinding
import com.ng.ngleetcode.old.model.tree.adapter.ArticleAdapter
import com.ng.ngleetcode.old.model.tree.web.WebActivity

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/15
 *    @description   :
 */
class TreeListActivity : BaseActivity<TreeViewModel, ActivityTreeListBinding>() {

    @Param
    private var systemId = 0

    @Param
    private var systemTitle = ""

    /**
     * 文章适配器
     */
    private lateinit var adapter: ArticleAdapter

    override fun initView(savedInstanceState: Bundle?) {

        MLog.d("params :$systemId $systemTitle")

        mBinding.tvTitle.text = systemTitle
        mBinding.ivBack.setOnClickListener {
            finish()
        }
        adapter = ArticleAdapter(this).apply {
            mBinding.rvTreeList.adapter = this

            setOnItemClickListener { i, _ ->
                val intent = Intent(this@TreeListActivity, WebActivity::class.java)
                val bundle = this@TreeListActivity.adapter.getBundle(i)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            setOnItemChildClickListener { i, view ->
                when (view.id) {
                    // nav().navigate(R.id.action_system_list_fragment_to_login_fragment)

                }

            }
        }
        //关闭更新动画
        (mBinding.rvTreeList.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mBinding.tvTitle.text = systemTitle
        //配置smartRefresh
        mBinding.smartRefresh.smartConfig()
        mBinding.smartRefresh.setOnRefreshListener {
            mViewModel.getArticleList(systemId)
        }
        mBinding.smartRefresh.setOnLoadMoreListener {
            mViewModel.loadMoreArticleList(systemId)
        }
//        mBinding.ivBack.clickNoRepeat {
//            nav().navigateUp()
//        }

    }

    override fun initObserve() {
        mViewModel.articleLiveData.observe(this, Observer {
            mBinding.smartRefresh.smartDismiss()
            mViewModel.defUI.dismissDialog
            adapter.submitList(it)
        })
        mViewModel.errorResult.observe(this, Observer {
            mBinding.smartRefresh.smartDismiss()
            if (it != null) {
                //显示网络错误
                mViewModel.defUI.showError
            }
        })
    }

    override fun onRetryBtnClick() {
        super.onRetryBtnClick()
        mViewModel.getArticleList(systemId)

    }

    override fun initData() {
        //自动刷新
        mViewModel.getArticleList(systemId)
        mViewModel.defUI.showDialog
    }

}