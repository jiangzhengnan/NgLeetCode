package com.ng.code.designmodel.结构型.门面模式;

/**
 * @author : 
 * @creation : 2022/10/04
 * @description :
 * 概念：
 * 也叫外观模式，为子系统提供一组统一的接口，定义一组高层接口让子系统更易用。
 * 简单来说，就是将多个接口调用替换为一个门面接口调用。
 */
public class 门面模式 {

    static class TextHelper {
        public void print() {
            System.out.println("写字");
        }
    }

    static class DrawHelper {
        public void print() {
            System.out.println("画图");
        }
    }

    static class PrinterManager {
        TextHelper mTextHelper;

        DrawHelper mDrawHelper;

        public PrinterManager(final TextHelper textHelper, final DrawHelper drawHelper) {
            mTextHelper = textHelper;
            mDrawHelper = drawHelper;
        }

        public void print() {
            if (mTextHelper != null) {
                mTextHelper.print();
            }
            if (mDrawHelper != null) {
                mDrawHelper.print();
            }
        }
    }

    public static void main(String[] args) {
        PrinterManager printerManager = new PrinterManager(new TextHelper(), new DrawHelper());
        printerManager.print();
    }

}
