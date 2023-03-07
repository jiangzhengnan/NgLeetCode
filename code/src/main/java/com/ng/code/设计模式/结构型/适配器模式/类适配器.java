package com.ng.code.设计模式.结构型.适配器模式;

/**
 * @author : 
 * @creation : 2022/10/04
 * @description :
 * 概念：
 * 将不兼容的接口转换为可兼容的接口，让原本由于接口不兼容而不能一起工作的类可以一起工作。(比如Android中列表的Adapter)
 * 分为类适配器，对象适配器
 * IDrawApi为绘图接口，Printer为不兼容IDrawApi的打印器功能类，Adapter通过继承或者组合兼容两种功能
 * 问题：
 * 应该选择类适配器还是对象适配器？
 * 如果Printer接口并不多，那两种实现方式都可以。
 * 如果Printer接口很多，而且Printer和IDrawApi接口定义大部分都相同，那我们推荐使用类适配器，因为Adaptor复用父类Printer的接口，比起对象适配器的实现方式，Adaptor的代码量要少一些。
 * 如果Printer接口很多，而且Printer和IDrawApi接口定义大部分都不相同，那我们推荐使用对象适配器，因为组合结构相对于继承更加灵活。
 * 示例：
 * 类适配器：基于继承实现
 */
public class 类适配器 {

    /**
     * 类适配器，基于继承
     */
    interface IDrawApi {
        void draw();
    }

    /**
     * 打印
     */
    static class Printer {
        public void print() {
            System.out.println("打印");
        }
    }

    /**
     * Adapter既可以绘画也可以打印
     */
    static class Adapter extends Printer implements IDrawApi {

        @Override
        public void draw() {
            System.out.println("绘画");
        }

        @Override
        public void print() {
            super.print();
        }
    }


    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.draw();
        adapter.print();
    }

}
