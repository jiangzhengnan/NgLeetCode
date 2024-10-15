
package com.ng.ngleetcode.ui.page.code.bean

import androidx.compose.runtime.mutableStateOf
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

data class CodeDirNode(override val childNode: MutableList<BaseNode>, val title: String) :
    BaseExpandNode() {

    val expandedState = mutableStateOf(false)

    init {
        isExpanded = false
    }

}