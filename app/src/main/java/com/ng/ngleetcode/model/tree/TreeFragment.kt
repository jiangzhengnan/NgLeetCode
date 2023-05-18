package com.ng.ngleetcode.model.tree


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ng.base.BaseFragment
import com.ng.base.utils.MLog
import com.ng.ngleetcode.constants.Constants
import com.ng.ngleetcode.databinding.FragmentTreeBinding
import com.ng.ngleetcode.model.tree.adapter.TreeAdapter


/**
 * todo des 广场 参考jetpack mvvm工程，广场
 * des 知识体系
 */
class TreeFragment : BaseFragment<TreeViewModel, FragmentTreeBinding>() {
    private var adapter: TreeAdapter? = null

    companion object {
        fun getInstance(title: String): TreeFragment {
            val fragment = TreeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initViewsAndEvents(v: View?, savedInstanceState: Bundle?) {
        adapter = TreeAdapter { i, j ->
            val treeId = adapter!!.data[i].children[j].id
            var treeTitle = adapter!!.data[i].children[j].name
            MLog.d("click tree:$treeId $treeTitle")
            val intent = Intent(activity, TreeListActivity::class.java)
            intent.putExtra(Constants.SYSTEM_TITLE, treeTitle)
            intent.putExtra(Constants.SYSTEM_ID, treeId)
            startActivity(intent)
        }
        mBinding.rvTree.adapter = adapter
    }

    override fun initData() {
        mViewModel.getSystemList()
    }

    override fun initObserve() {
        mViewModel.systemLiveData.observe(this, Observer {
            adapter?.setNewData(it)
        })
    }
}
