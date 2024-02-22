package com.cj.design.desiginmodel.行为型.模板方法模式;

/**
 * 概念：
 * 全称是模板方法设计模式，在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现
 * 简单的说就是通过抽象方法让子类继承去实现部分逻辑
 * 模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤
 * 模板模式有两大作用：复用和扩展
 * 示例：
 * 绘制一个画布，需要分别绘制背景层与内容层
 */
public class TemplateMethodPattern {
    // 抽象类，定义炒菜的步骤
    abstract static class CookDish {
        // 模板方法，规定炒菜的骨架
        public void cook() {
            prepareIngredient();
            cutIngredient();
            heatPan();
            addOil();
            arrangeIngredient();
            stirFry();
            serveDish();
        }

        // 抽象方法，子类需要实现
        abstract void prepareIngredient();

        // 抽象方法，子类需要实现
        abstract void cutIngredient();

        // 钩子方法，提供默认实现
        void heatPan() {
            System.out.println("加热锅子");
        }

        // 钩子方法，提供默认实现
        void addOil() {
            System.out.println("倒入油");
        }

        // 抽象方法，子类需要实现
        abstract void arrangeIngredient();

        // 抽象方法，子类需要实现
        abstract void stirFry();

        // 钩子方法，提供默认实现
        void serveDish() {
            System.out.println("出锅啦！");
        }
    }

    // 炒青菜，继承抽象类
    static class StirFriedVegetables extends CookDish {
        @Override
        void prepareIngredient() {
            System.out.println("准备菜叶");
        }

        @Override
        void cutIngredient() {
            System.out.println("切成小段");
        }

        @Override
        void arrangeIngredient() {
            System.out.println("摆放在盘子里");
        }

        @Override
        void stirFry() {
            System.out.println("翻炒青菜");
        }
    }

    // 炒土豆丝，继承抽象类
    static class StirFriedPotatoes extends CookDish {
        @Override
        void prepareIngredient() {
            System.out.println("准备土豆");
        }

        @Override
        void cutIngredient() {
            System.out.println("切成丝");
        }

        @Override
        void arrangeIngredient() {
            System.out.println("摆放在盘子里");
        }

        @Override
        void stirFry() {
            System.out.println("翻炒土豆丝");
        }

        @Override
        void addOil() {
            System.out.println("倒入花生油");
        }
    }

    /**
     * 客户端调用
     * <p>
     * CookDish 是抽象类，其中 cook() 是模板方法，它规定了炒菜的步骤，包含了一些可变的步骤，如 prepareIngredient()、cutIngredient()
     * 、arrangeIngredient() 和 stirFry()，以及一些钩子方法，如 heatPan() 和 addOil()
     * 。这些钩子方法提供了默认实现，但是可以由子类选择性的进行覆盖。
     * StirFriedVegetables 和 StirFriedPotatoes 是具体子类。它们实现了抽象类中的抽象方法，实现了自己的具体炒菜方法，并且可以覆盖抽象类中的钩子方法。
     * 在客户端中，我们使用不同的子类来制定不同的炒菜，同时通过调用 cook() 方法来执行炒菜过程。
     */
    public static void main(String[] args) {
        System.out.println("开始炒青菜：");
        CookDish dish1 = new StirFriedVegetables();
        dish1.cook();

        System.out.println();
        System.out.println("开始炒土豆丝：");
        CookDish dish2 = new StirFriedPotatoes();
        dish2.cook();
    }

}
