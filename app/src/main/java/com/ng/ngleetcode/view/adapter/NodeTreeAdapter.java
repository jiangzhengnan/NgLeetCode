package com.ng.ngleetcode.view.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ng.code.util.tree.CodeDirNode;
import com.ng.code.util.tree.CodeNode;
import com.ng.ngleetcode.view.provider.CodeDirProvider;
import com.ng.ngleetcode.view.provider.CodeProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NodeTreeAdapter extends BaseNodeAdapter {

    public NodeTreeAdapter() {
        super();
        addNodeProvider(new CodeDirProvider());
        addNodeProvider(new CodeProvider());
    }

    public interface OnLeftItemClick {
        void onItem(int pos);
    }

    private OnLeftItemClick onLeftItemClick;

    public void setOnLeftItemClick(OnLeftItemClick onLeftItemClick) {
        this.onLeftItemClick = onLeftItemClick;
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof CodeDirNode) {
            return 2;
        } else if (node instanceof CodeNode) {
            return 3;
        }
        return -1;
    }

    @Override
    protected void setOnItemClick(@NotNull View v, int position) {
        super.setOnItemClick(v, position);
    }

    @Override
    protected void setOnItemChildClick(@NotNull View v, int position) {
        super.setOnItemChildClick(v, position);
    }

    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;
}
