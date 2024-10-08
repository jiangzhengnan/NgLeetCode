package com.ng.ngleetcode.old.model.code.adapter.provider

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ng.ngleetcode.R
import com.ng.ngleetcode.old.model.code.adapter.NodeTreeAdapter
import com.ng.ngleetcode.old.model.code.bean.CodeNode

class CodeProvider(private var onLeftItemClick: NodeTreeAdapter.OnLeftItemClick?) : BaseNodeProvider() {
    override val itemViewType = 1

    fun setOnLeftItemClick(onLeftItemClick: NodeTreeAdapter.OnLeftItemClick?) {
        this.onLeftItemClick = onLeftItemClick
    }

    override val layoutId: Int
        get() = R.layout.item_node_code_code

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity = data as CodeNode
        helper.setText(R.id.title, entity.title)
        if (data.state == 1) {
            (helper.getView<View>(R.id.title) as TextView).setTextColor(Color.parseColor("#00800D"))
        } else {
            (helper.getView<View>(R.id.title) as TextView).setTextColor(Color.BLACK)
        }

        helper.getView<LinearLayout>(R.id.layout_code_item).setOnClickListener(View.OnClickListener {
            if (onLeftItemClick != null) {
                onLeftItemClick!!.onItem(data)
            }
        })
    }
}