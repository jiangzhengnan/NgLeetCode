package com.ng.code.util.tree;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.node.BaseNode;

import java.util.List;

public class CodeNode extends BaseNode {
    public String title;
    public int id;
    public String contentPath;
    public String content;
    public int state;

    @Nullable
    public String getMenu() {
        return contentPath == null ? null : contentPath.split("/")[0];
    }

    public CodeNode(String title, int id, String contentPath, String content) {
        this.title = title;
        this.id = id;
        this.contentPath = contentPath;
        this.content = content;
    }

    public CodeNode(String s, String content) {
        this.title = s;
        this.content = content;
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
