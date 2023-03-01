package com.ng.ngleetcode.view.provider;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ng.base.LogUtil;
import com.ng.code.util.model.CodeDataModel;
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
        int state = CodeDataModel.getInstance().loopCodeState(getContext(), entity.title, -1);
        if (state == 2) {
            ((TextView) helper.getView(R.id.title)).setTextColor(Color.parseColor("#00800D"));
        } else {
            ((TextView) helper.getView(R.id.title)).setTextColor(Color.BLACK);
        }

        helper.findView(R.id.layout_code_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLeftItemClick != null) {
                    CodeNode codeNode = (CodeNode) data;
                    onLeftItemClick.onItem(codeNode);
                }
            }
        });
    }


}
