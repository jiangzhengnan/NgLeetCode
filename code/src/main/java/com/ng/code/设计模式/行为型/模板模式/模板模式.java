package com.ng.code.设计模式.行为型.模板模式;

/**
 * @author : 
 * @creation : 2022/10/05
 * @description :
 * 概念：
 * 全称是模板方法设计模式，在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现
 * 简单的说就是通过抽象方法让子类继承去实现部分逻辑
 * 模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤
 * 模板模式有两大作用：复用和扩展
 * 示例：
 * 绘制一个画布，需要分别绘制背景层与内容层
 */
public class 模板模式 {

    static abstract class AbsCanvas {

        public final void draw() {
            drawBg();
            drawContent();
        }

        protected abstract void drawBg();

        protected abstract void drawContent();
    }

    static class CircleCanvas extends AbsCanvas {

        @Override
        protected void drawBg() {
            System.out.println("绘制圆形背景");
        }

        @Override
        protected void drawContent() {
            System.out.println("绘制圆形内容");
        }
    }

    static class SquareCanvas extends AbsCanvas {

        @Override
        protected void drawBg() {
            System.out.println("绘制方形背景");
        }

        @Override
        protected void drawContent() {
            System.out.println("绘制方形内容");
        }
    }

    public static void main(String[] args) {
        AbsCanvas circleCanvas = new CircleCanvas();
        AbsCanvas squareCanvas = new SquareCanvas();
        circleCanvas.draw();
        squareCanvas.draw();
    }

}
