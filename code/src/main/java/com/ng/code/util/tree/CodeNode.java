package com.ng.code.util.tree;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ng.code.util.CodeBean;

import java.util.List;

public class CodeNode extends BaseNode {
    private String title;
    private CodeBean codeBean;

    public CodeNode(String title, CodeBean codeBean) {
        this.title = title;
        this.codeBean = codeBean;
    }


    public String getTitle() {
        return title;
    }

    public CodeBean getCodeBean() {
        return codeBean;
    }

    public void setCodeBean(CodeBean codeBean) {
        this.codeBean = codeBean;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
