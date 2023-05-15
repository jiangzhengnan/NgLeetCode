package com.ng.ngleetcode.model.code.adapter.provider

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ng.ngleetcode.R
import com.ng.ngleetcode.model.code.adapter.NodeTreeAdapter
import com.ng.ngleetcode.model.code.bean.CodeDirNode

class CodeDirProvider : BaseNodeProvider() {
    override val itemViewType = 2
    override val layoutId = R.layout.item_node_code_dir

    override fun convert(helper: BaseViewHolder, data: BaseNode) {
        val entity = data as CodeDirNode
        helper.setText(R.id.title, entity.title)
        helper.setImageResource(R.id.iv, R.drawable.ic_iv_right)
        setArrowSpin(helper, data, false)
    }

    override fun convert(helper: BaseViewHolder, item: BaseNode, payloads: List<Any>) {
        for (payload in payloads) {
            if (payload is Int && payload == NodeTreeAdapter.EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, item, true)
            }
        }
    }

    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {
        val entity = data as CodeDirNode
        val imageView = helper.getView<ImageView>(R.id.iv)
        if (entity.isExpanded) {
            if (isAnimate) {
                ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(DecelerateInterpolator())
                    .rotation(0f)
                    .start()
            } else {
                imageView.rotation = 0f
            }
        } else {
            if (isAnimate) {
                ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(DecelerateInterpolator())
                    .rotation(90f)
                    .start()
            } else {
                imageView.rotation = 90f
            }
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        getAdapter()!!.expandOrCollapse(
            position,
            true,
            true,
            NodeTreeAdapter.EXPAND_COLLAPSE_PAYLOAD
        )
    }
}