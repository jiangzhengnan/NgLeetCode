package com.ng.code.设计模式.结构型.装饰器模式;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/10/01
 * @description :
 * 概念：
 * 装饰器模式是指给一个类增强一些方法，对其做一些包装，但是不会影响改变原本类。
 * 异同：
 * 1.与代理模式的区别
 * 装饰器模式看起来跟代理模式特别的相似，但是
 * 对于代理模式来说可以作出一些操作改变原有代码，也就是说带有侵入性。
 * 装饰者模式侧重于添加添加额外的功能职责，也可以重叠使用。
 * 2.与继承的区别：
 * 继承设计子类，是在编译时静态决定的，通过组合的做法扩展对象，可以在运行时动态地进行扩展
 * 装饰者模式通过组合和委托，可以在运行时动态地为对象加上新的行为
 * 示例：
 * 需要一个画图api，在draw之后，需要设置颜色和形状
 */
public class 装饰器模式 {

    //画图api抽象类
    static abstract class DrawComponent {
        abstract void draw();
    }

    static class DrawApi extends DrawComponent {

        @Override
        void draw() {
            System.out.println("画图");
        }
    }

    /**
     * Decorator，装饰抽象类，继承了Component
     * 从外类来扩展Component类的功能，但对于Component来说，
     * 是无需知道Decorator的存在的
     */
    static abstract class DrawDecorator extends DrawComponent {
        protected DrawComponent component;

        public DrawComponent getComponent() {
            return component;
        }

        public void setComponent(final DrawComponent component) {
            this.component = component;
        }

        @Override
        void draw() {
            if (component != null) {
                component.draw();
            }
        }
    }

    static class ShapeDrawDecorator extends DrawDecorator {
        @Override
        void draw() {
            super.draw();
            System.out.println("设置形状");
        }
    }

    static class ColorDrawDecorator extends DrawDecorator {
        @Override
        void draw() {
            super.draw();
            System.out.println("设置颜色");
        }
    }

    public static void main(String[] args) {
        DrawApi drawApi = new DrawApi();
        ShapeDrawDecorator shapeDrawDecorator = new ShapeDrawDecorator();
        shapeDrawDecorator.setComponent(drawApi);
        ColorDrawDecorator colorDrawDecorator = new ColorDrawDecorator();
        colorDrawDecorator.setComponent(shapeDrawDecorator);
        colorDrawDecorator.draw();
    }

}
