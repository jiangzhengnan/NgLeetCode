package com.ng.ngleetcode.old;

import java.util.List;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

public class HeadLayoutNode extends BaseExpandNode {

    private String title;

    public HeadLayoutNode(String title) {
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
