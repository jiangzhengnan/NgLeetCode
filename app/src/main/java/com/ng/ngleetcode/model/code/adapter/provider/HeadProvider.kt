package com.ng.ngleetcode.model.code.adapter.provider

import android.view.View
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ng.ngleetcode.R
import com.ng.ngleetcode.model.code.data.ProblemAndroidUtil

class HeadProvider : BaseNodeProvider() {
    override val itemViewType = 1
    override val layoutId = R.layout.item_node_first

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        helper.setText(R.id.navHeaderMainDesc, ProblemAndroidUtil.getNowProgressAndroid(context))
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        //getAdapter().expandOrCollapse(position, true, true, NodeTreeAdapter.EXPAND_COLLAPSE_PAYLOAD);
    }
}