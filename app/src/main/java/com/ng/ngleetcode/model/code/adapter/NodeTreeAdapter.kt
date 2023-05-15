package com.ng.ngleetcode.model.code.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.ngleetcode.model.code.adapter.provider.CodeDirProvider
import com.ng.ngleetcode.model.code.adapter.provider.CodeProvider
import com.ng.ngleetcode.model.code.bean.CodeDirNode
import com.ng.ngleetcode.model.code.bean.CodeNode

/**
 *    @author : jiangzhengnan.jzn@alibaba-inc.com
 *    @creation   : 2023/05/15
 *    @description   :
 */
class NodeTreeAdapter(onLeftItemClick: OnLeftItemClick) : BaseNodeAdapter() {
    companion object {
        @kotlin.jvm.JvmField
        val EXPAND_COLLAPSE_PAYLOAD = 110
    }

    init {
        //addNodeProvider(HeadProvider())
        addNodeProvider(CodeDirProvider())
        addNodeProvider(
            CodeProvider(
                onLeftItemClick
            )
        )
    }

    interface OnLeftItemClick {
        fun onItem(codeBean: CodeNode?)
    }


    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when (data[position]) {
            is CodeDirNode -> {
                2
            }
            is CodeNode -> {
                1
            }
            else -> 1
        }
    }

}