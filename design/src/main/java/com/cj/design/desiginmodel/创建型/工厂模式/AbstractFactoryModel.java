package com.cj.design.desiginmodel.创建型.工厂模式;

/**
 * 抽象工厂模式:
 * 可以让一个工厂负责创建多个不同类型的对象（IRuleConfigParser、ISystemConfigParser等），
 * 而不是只创建一种parser对象。这样就可以有效地减少工厂类的个数
 */
public class AbstractFactoryModel {

    interface Fruit {
        void introduce();
    }

    static class Apple implements Fruit {
        @Override
        public void introduce() {
            System.out.println("I'm an apple.");
        }
    }

    static class Banana implements Fruit {
        @Override
        public void introduce() {
            System.out.println("I'm a banana.");
        }
    }

    interface Juice {
        void introduce();
    }

    static class AppleJuice implements Juice {
        @Override
        public void introduce() {
            System.out.println("I'm apple juice.");
        }
    }

    static class BananaJuice implements Juice {
        @Override
        public void introduce() {
            System.out.println("I'm banana juice.");
        }
    }

    interface AbstractFactory {
        Fruit createFruit();

        Juice createJuice();
    }

    static class AppleFactory implements AbstractFactory {
        @Override
        public Fruit createFruit() {
            return new Apple();
        }

        @Override
        public Juice createJuice() {
            return new AppleJuice();
        }
    }

    static class BananaFactory implements AbstractFactory {
        @Override
        public Fruit createFruit() {
            return new Banana();
        }

        @Override
        public Juice createJuice() {
            return new BananaJuice();
        }
    }

    public static void main(String[] args) {
        AbstractFactory appleFactory = new AppleFactory();
        Fruit apple = appleFactory.createFruit();
        apple.introduce();  // I'm an apple.

        Juice appleJuice = appleFactory.createJuice();
        appleJuice.introduce();  // I'm apple juice.

        AbstractFactory bananaFactory = new BananaFactory();
        Fruit banana = bananaFactory.createFruit();
        banana.introduce(); // I'm a banana.

        Juice bananaJuice = bananaFactory.createJuice();
        bananaJuice.introduce(); // I'm banana juice.
    }

}
