package com.ng.code.designmodel.结构型.代理模式;

/**
 * @author : 
 * @creation : 2022/10/01
 * @description :
 * 概念：
 * 在不直接修改原有类的前提下对原有类的方法功能进行扩展。
 * 通过引入代理类(构参带入)来给原始类附加功能
 * 示例：
 * 定义一个绘制api DrawApi， 需要在绘制前后进行一些扩展功能(打点，监控等)
 * 缺点：
 * 1：如果原始类有多个方法，我们需要在代理类中，将原始类中的所有的方法，都重新实现一遍
 * 2：如果存在多个被代理类(比如下面的绘制 和 测算两个类) 都需要通过代理模式添加特殊功能，
 * 则需要针对每个被代理类都创建一个代理类
 */
public class 静态代理 {

    //定义一个绘制接口(原始类)
    interface DrawApi {
        void draw();

        void refresh();
    }

    //定义具体的圆形绘制方案，被代理类
    static class CircleDraw implements DrawApi {

        @Override
        public void draw() {
            System.out.println("画一个圆");
        }

        @Override
        public void refresh() {
            System.out.println("刷新");
        }
    }

    //定义一个测算接口，原始类
    interface CalculateApi {
        void calculate();
    }

    //定义一个绘制代理类，代理实现具体的绘制功能，代理类
    static class DrawProxy implements DrawApi {
        //被代理的绘制方案
        private DrawApi target;

        //构造器
        public DrawProxy() {
            this.target = new CircleDraw();
        }

        @Override
        public void draw() {
            before();
            target.draw();
            after();
        }

        @Override
        public void refresh() {
            //缺点：我们需要在代理类中，将原始类中的所有的方法，都重新实现一遍
        }

        private void after() {
            System.out.println("执行后 做一些事情");
        }

        private void before() {
            System.out.println("执行前 做一些事情");
        }

    }

    public static void main(String[] args) {
        //使用静态代理
        new DrawProxy().draw();
        // 如果需要扩展其他原始类，则这样行不通
        //new DrawProxy(new CalculateApi()).draw();
        //线程也用到了代理模式，传入不同的runnable实现
        //new Thread(实现runnable接口的实例化对象).start();
    }

}
