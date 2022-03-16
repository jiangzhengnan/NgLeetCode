package com.ng.ngleetcode.view.provider;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ng.ngleetcode.R;

import org.jetbrains.annotations.NotNull;

public class HeadProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_first;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
//        HeadNode entity = (HeadNode) data;
//        helper.setText(R.id.title, entity.getTitle());
//        helper.setImageResource(R.id.iv, R.mipmap.arrow_r);

    }



    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        //getAdapter().expandOrCollapse(position, true, true, NodeTreeAdapter.EXPAND_COLLAPSE_PAYLOAD);
    }
}
