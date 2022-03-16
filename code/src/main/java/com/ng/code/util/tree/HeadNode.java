package com.ng.code.util.tree;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import java.util.List;

public class HeadNode extends BaseExpandNode {

    private String title;

    public HeadNode(String title) {
        this.title = title;

        setExpanded(false);
    }

    public String getTitle() {
        return title;
    }


    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
