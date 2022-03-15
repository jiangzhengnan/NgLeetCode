package com.ng.code.util;

public class CodeBean {
    public int id;
    public String title;
    public String contentPath;
    public String content;

    public CodeBean(int id, String title, String contentPath, String content) {
        this.id = id;
        this.title = title;
        this.contentPath = contentPath;
        this.content = content;
    }

    public CodeBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CodeBean{" +
                "title='" + title + '\'' +
                ", contentPath='" + contentPath + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}