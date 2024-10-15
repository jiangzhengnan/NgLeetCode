package com.ng.ngleetcode.ui.page.code.bean

import com.chad.library.adapter.base.entity.node.BaseNode

data class CodeNode(
    var title: String = "", var id: Int = 0, var contentPath: String = "", var content: String = ""
) : BaseNode() {

    @JvmField
    var state: Int = 0

    fun getMenuStr(): String? {
        return if (contentPath.isEmpty()) null else contentPath.split("/".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()[0]
    }

    override fun toString(): String {
        return "CodeNode(title='$title', id=$id, contentPath='$contentPath', state=$state, childNode=$childNode)"
    }


    override val childNode: MutableList<BaseNode>? = null


}