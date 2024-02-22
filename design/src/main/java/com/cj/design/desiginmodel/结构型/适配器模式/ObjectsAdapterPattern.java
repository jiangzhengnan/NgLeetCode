package com.cj.design.desiginmodel.结构型.适配器模式;

/**
 * 对象适配器：基于组合实现
 */
public class ObjectsAdapterPattern {

    // 目标接口
    interface Target {
        void request();
    }

    // 需要被适配的类
    static class Adaptee {
        void specialRequest() {
            System.out.println("特殊请求");
        }
    }

    // 对象适配器
    static class ObjectAdapter implements Target {
        private Adaptee adaptee;

        public ObjectAdapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            adaptee.specialRequest();
        }
    }

    // 客户端调用
    public static void main(String[] args) {
        Target target = new ObjectAdapter(new Adaptee());
        target.request();
    }
}
