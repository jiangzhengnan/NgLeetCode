package com.ng.code.designmodel.创建型.建造者模式;

import android.text.TextUtils;

/**
 * @author : 
 * @creation : 2022/10/01
 * @description :
 */
public class TestClass {
    private String name;
    private int maxTotal;

    private TestClass(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
    }
    //...省略getter方法...

    //我们将Builder类设计成了TestClass的内部类。
    //我们也可以将Builder类设计成独立的非内部类TestClassBuilder。
    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;

        public TestClass build() {
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
            if (TextUtils.isEmpty(name)) {
                throw new IllegalArgumentException("...");
            }
            if (maxTotal < DEFAULT_MAX_TOTAL) {
                throw new IllegalArgumentException("...");
            }
            return new TestClass(this);
        }

        public Builder setName(String name) {
            if (TextUtils.isEmpty(name)) {
                throw new IllegalArgumentException("...");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxTotal = maxTotal;
            return this;
        }
    }

    public static void main(String[] args) {

    }
}

