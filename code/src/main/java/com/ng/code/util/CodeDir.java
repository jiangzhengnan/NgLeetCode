package com.ng.code.util;

import java.util.List;

public class CodeDir {
    public int id;
    public String title;
    public List<CodeBean> dataList;

    public CodeDir() {
    }

    public CodeDir(int id) {
        this.id = id;
    }

    public CodeDir(String title, List<CodeBean> dataList) {
        this.title = title;
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CodeDir{" +
                "title='" + title + '\'' +
                ", dataList=" + dataList +
                '}';
    }
}
