package com.ng.code.util;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/02/28
 * @description :
 */
public class MyCodeProblem {

    public String name;
    public String pkgName;
    // https://github.com/jiangzhengnan/NgLeetCode/blob/master/app/src/main/java/com/ng/ngleetcode/code/%E9%93%BE%E8%A1%A8/%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACn%E4%B8%AA%E8%8A%82%E7%82%B9.java
    public String link;

    public MyCodeProblem(String name, String pkgName, String link) {
        this.name = name;
        this.pkgName = pkgName;
        this.link = link;
    }

    @Override
    public String toString() {
        return "MyCodeProblem{" +
                "name='" + name + '\'' +
                ", pkgName='" + pkgName + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
