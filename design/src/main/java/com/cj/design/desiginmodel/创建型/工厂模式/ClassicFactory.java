package com.cj.design.desiginmodel.创建型.工厂模式;

import java.util.HashMap;
import java.util.Map;

/**
 * 经典工厂模式
 * <p>
 * 与简单
 */
public class ClassicFactory {

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

    interface FruitFactory {
        Fruit createFruit();
    }

    static class AppleFactory implements FruitFactory {
        @Override
        public Fruit createFruit() {
            return new Apple();
        }
    }

    static class BananaFactory implements FruitFactory {
        @Override
        public Fruit createFruit() {
            return new Banana();
        }
    }

    static class FruitFactoryMap {//工厂的工厂
        public static final Map<String, FruitFactory> cachedFactories = new HashMap<>();

        static {
            cachedFactories.put("apple", new AppleFactory());
            cachedFactories.put("banana", new BananaFactory());
        }

        public static Fruit createFruit(String name) throws Exception {
            FruitFactory fruitFactory = cachedFactories.get(name);
            if (fruitFactory != null) {
                return fruitFactory.createFruit();
            } else {
                throw new Exception("There is no Fruit named '" + name + "'.");
            }
        }
    }

    public static void main(String[] args) {

        /**
         * 这也是一种工厂模式，具体来说，它是一个简单工厂模式和抽象工厂模式的混合体。这种方式使用了两级工厂，
         * 即一个工厂的工厂，将原本的工厂抽象化，从而进一步提高了代码的灵活性和可扩展性。
         * 主程序中调用 FruitFactoryMap.createFruit 方法来创建不同种类的水果对象，并调用它们的 introduce
         * 方法来展示不同的信息。整个程序中，没有直接引用具体的工厂类和产品类，提高了代码的可维护性和重用性。
         *
         * 这种方式仍然需要修改工厂类 FruitFactoryMap 来添加新的产品类型，因此也不满足开闭原则。
         * 虽然我们使用了工厂的工厂来提高可扩展性，但是仍然需要在代码中修改工厂类定义。
         * 正是因为这种工厂的工厂方式不是专门针对扩展进行设计的，因此并没有很好地解决工厂模式的最大问题——添加新的产品或工厂时需要修改源代码，
         * 并且会带来重编译的代价。考虑到这个问题，我们通常会选择使用其他更加专业并且高级的设计模式，如依赖注入、反射、配置文件等等，来实现更加灵活和可扩展的工厂。
         */
        try {
            Fruit apple = null;
            apple = FruitFactoryMap.createFruit("apple");
            Fruit banana = FruitFactoryMap.createFruit("banana");
            apple.introduce();   // I'm an apple.
            banana.introduce();  // I'm a banana.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        try {
//            FruitFactory appleFactory = new AppleFactory();
//            Fruit apple = appleFactory.createFruit();
//            apple.introduce();  // I'm an apple.
//
//            FruitFactory bananaFactory = new BananaFactory();
//            Fruit banana = bananaFactory.createFruit();
//            banana.introduce();  // I'm a banana.
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

}
