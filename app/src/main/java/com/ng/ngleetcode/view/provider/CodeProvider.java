package com.ng.ngleetcode.view.provider;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ng.code.util.tree.CodeNode;
import com.ng.ngleetcode.R;
import com.ng.ngleetcode.view.adapter.NodeTreeAdapter;

import org.jetbrains.annotations.NotNull;

public class CodeProvider extends BaseNodeProvider {

    public CodeProvider(NodeTreeAdapter.OnLeftItemClick onLeftItemClick) {
        this.onLeftItemClick = onLeftItemClick;
    }

    @Override
    public int getItemViewType() {
        return 3;
    }

    private NodeTreeAdapter.OnLeftItemClick onLeftItemClick;

    public void setOnLeftItemClick(NodeTreeAdapter.OnLeftItemClick onLeftItemClick) {
        this.onLeftItemClick = onLeftItemClick;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_node_code_code;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data) {
        CodeNode entity = (CodeNode) data;
        helper.setText(R.id.title, entity.getTitle());
        helper.findView(R.id.layout_code_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLeftItemClick != null) {
                    CodeNode codeNode = (CodeNode) data;
                    onLeftItemClick.onItem(codeNode.getCodeBean());
                }
            }
        });
    }


}
