package com.ng.ngleetcode.view.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ng.code.util.CodeBean;
import com.ng.code.util.tree.CodeDirNode;
import com.ng.code.util.tree.CodeNode;
import com.ng.code.util.tree.HeadNode;
import com.ng.ngleetcode.view.provider.CodeDirProvider;
import com.ng.ngleetcode.view.provider.CodeProvider;
import com.ng.ngleetcode.view.provider.HeadProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NodeTreeAdapter extends BaseNodeAdapter {

    private BaseNode nowItem;

    public NodeTreeAdapter(OnLeftItemClick onLeftItemClick) {
        super();
        addNodeProvider(new HeadProvider());
        addNodeProvider(new CodeDirProvider());
        addNodeProvider(new CodeProvider(onLeftItemClick));
    }


    public interface OnLeftItemClick {
        void onItem(CodeBean codeBean);
    }


    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof HeadNode) {
            return 1;
        } else if (node instanceof CodeDirNode) {
            return 2;
        } else if (node instanceof CodeNode) {
            return 3;
        }
        return -1;
    }

    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;
}
