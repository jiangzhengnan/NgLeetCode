
package com.ng.ngleetcode.model.code.bean

import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

data class CodeDirNode(override val childNode: MutableList<BaseNode>, val title: String) :
    BaseExpandNode() {

    init {
        isExpanded = false
    }

}