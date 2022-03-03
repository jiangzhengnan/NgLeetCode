package com.ng.code.util;

public class CodeBean {
    public String title;
    public String content;

    public CodeBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CodeBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
