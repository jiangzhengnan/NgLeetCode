package com.cj.design.desiginmodel.创建型.工厂模式;

/**
 * 简单工厂模式
 * 通过hashMap形成映射，形成工厂类->对应实例的桥梁
 */
public class SimpleFactory {
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

    static class FruitFactory {

        public static Fruit createFruit(String name) throws Exception {
            if (name.equalsIgnoreCase("apple")) {
                return new Apple();
            } else if (name.equalsIgnoreCase("banana")) {
                return new Banana();
            } else {
                throw new Exception("There is no Fruit named '" + name + "'.");
            }
        }
    }

    public static void main(String[] args) {
        try {
            Fruit apple = null;
            apple = FruitFactory.createFruit("apple");
            Fruit banana = FruitFactory.createFruit("banana");
            apple.introduce();   // I'm an apple.
            banana.introduce();  // I'm a banana.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
