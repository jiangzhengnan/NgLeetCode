package com.ng.ngleetcode.old.model.code.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.ng.ngleetcode.old.model.code.adapter.provider.CodeDirProvider
import com.ng.ngleetcode.old.model.code.adapter.provider.CodeProvider
import com.ng.ngleetcode.old.model.code.adapter.provider.HeadProvider
import com.ng.ngleetcode.old.model.code.bean.CodeDirNode
import com.ng.ngleetcode.old.model.code.bean.CodeNode
import com.ng.ngleetcode.old.model.code.bean.CodeProgressNode

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
        addNodeProvider(HeadProvider())
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
            is CodeProgressNode -> {
                3
            }
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