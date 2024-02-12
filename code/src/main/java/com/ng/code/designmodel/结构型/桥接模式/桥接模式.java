package com.ng.code.designmodel.结构型.桥接模式;

/**
 * @author : 
 * @creation : 2022/10/01
 * @description :
 * 概念：
 * 与代理模式容易混淆。一个类存在两个（或多个）独立变化的维度，我们通过组合的方式，让这两个（或多个）维度可以独立进行扩展。
 * 通俗的说，就是一个类如果有多个属性需要变化，在进行扩展(继承)时会导致新增的子类指数增加，所以需要通过桥接模式将继承改为组合的方式，
 * 将属性的维度解藕出来，将抽象与原本复杂的实现分离，提高扩展能力。
 * 区别：
 * 与代理模式相比，假设将一个纬度抽象以后，执行的方法命名为a()
 * 代理模式侧重于 通过代理的方式扩展被代理类的功能
 * 桥接模式侧重于 通过组合的方式让不同的纬度的原始类可以搭配使用
 * 示例:
 * 以Android画图举例，需要提供一个接口DrawApi，可以绘制三种颜色，与三种形状，普通的做法是通过子类实现不同的组合，一共有9种子类。
 * 这里把颜色解藕然后通过构参带入，把形状解藕为通过子类实现，使其变为两个自由的纬度，通过组合关系（也就是桥梁）任意组合在一起,
 * 可以大幅减少实现的类型。
 */
public class 桥接模式 {

    //颜色抽象化，原始类
    interface Color {
        String getColor();
    }

    //具体的红色，原始类实现
    static class RedColor implements Color {
        @Override
        public String getColor() {
            return "红色";
        }
    }

    //创建桥接的实现接口。画图api
    static abstract class DrawApi {
        protected Color mColor;

        public DrawApi(final Color color) {
            mColor = color;
        }

        abstract void draw();
    }

    //桥接模式实现类
    static class CircleDraw extends DrawApi {
        public CircleDraw(final Color color) {
            super(color);
        }

        @Override
        void draw() {
            System.out.println("画一个 " + mColor.getColor() + " 的圆形");
        }
    }

    public static void main(String[] args) {
        DrawApi drawApi = new CircleDraw(new RedColor());
        drawApi.draw();
    }
}
