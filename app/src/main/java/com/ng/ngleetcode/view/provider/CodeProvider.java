package com.ng.ngleetcode.view.provider;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ng.code.util.tree.CodeNode;
import com.ng.ngleetcode.R;

import org.jetbrains.annotations.NotNull;

public class CodeProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_code_code;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
        CodeNode entity = (CodeNode) data;
        helper.setText(R.id.title, entity.getTitle());
    }
}
