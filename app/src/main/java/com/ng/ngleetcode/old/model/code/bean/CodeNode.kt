package com.ng.ngleetcode.old.model.code.bean

import com.chad.library.adapter.base.entity.node.BaseNode

data class CodeNode(
    var title: String, var id: Int, var contentPath: String?, var content: String
) : BaseNode() {

    @kotlin.jvm.JvmField
    var state: Int = 0

    fun getMenuStr(): String? {
        return if (contentPath == null) null else contentPath!!.split("/".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()[0]
    }


    override val childNode: MutableList<BaseNode>? = null


}