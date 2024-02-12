package com.ng.code.designmodel.结构型.适配器模式;

/**
 * @author : 
 * @creation : 2022/10/04
 * @description :
 * 示例：
 * 对象适配器：基于组合实现
 */
public class 对象适配器 {

    /**
     * 绘图
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
    static class Adapter implements IDrawApi {
        private Printer mPrinter;

        public Adapter(final Printer printer) {
            mPrinter = printer;
        }

        @Override
        public void draw() {
            System.out.println("绘画");
        }

        public void print() {
            mPrinter.print();
        }
    }


    public static void main(String[] args) {
        Adapter adapter = new Adapter(new Printer());
        adapter.draw();
        adapter.print();
    }

}
