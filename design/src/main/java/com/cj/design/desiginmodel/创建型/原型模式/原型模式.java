package com.cj.design.desiginmodel.创建型.原型模式;

/**
 * 概念：
 * 如果对象的创建成本比较大（对象中的数据需要经过复杂的计算才能得到（比如排序、计算哈希值），
 * 或者需要从RPC、网络、数据库、文件系统等非常慢速的IO中读取），而同一个类的不同对象之间差别不大（大部分字段都相同），
 * 在这种情况下，我们可以利用对已有对象（原型）进行复制（或者叫拷贝）的方式来创建新对象，以达到节省创建时间的目的。
 * 这种基于原型来创建对象的方式就叫作原型设计模式（Prototype Design Pattern），简称原型模式。
 * 浅拷贝和深拷贝的区别在于，浅拷贝只会复制图中的索引（散列表），不会复制数据（SearchWord对象）本身。
 * 相反，深拷贝不仅仅会复制索引，还会复制数据本身。
 */
public class 原型模式 {
    // 定义Food类
    static class Food{
        String food;
        // get set ...
        public Food(String food) {
            this.food = food;
        }

        public String getFood() {
            return food;
        }

        public void setFood(final String food) {
            this.food = food;
        }
    }

    // 定义Student类
    static class Student implements Cloneable{ // Cloneable接口

        @Override
        public Object clone() throws CloneNotSupportedException { // 提升访问权限
            Student student = (Student) super.clone();
            //针对成员变量进行拷贝
            student.name = new String(name);
            student.food = new Food(food.getFood());
            return student;
        }

        private Food food;
        private String name;
        int age;

        public Student() {
        }

        public Student(String name, int age, Food food) {
            this.name = name;
            this.age = age;
            this.food = food;
        }
        //get set..


        public Food getFood() {
            return food;
        }

        public void setFood(final Food food) {
            this.food = food;
        }
    }


    public static void main(String[] args) {
        //测试
        Food food  = new Food("apple");
        System.out.println(food); // JavaPackage_1.Food@6d311334

        //Java深拷贝，对内层的对象也进行拷贝。
        Student student1 = new Student("stu", 29,food);
        // 通过student1克隆student2，不通过new对象的形式创建
        Student student2 = null;
        try {
            student2 = (Student) student1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(student1); // JavaPackage_1.Student@3d075dc0
        System.out.println(student2); // JavaPackage_1.Student@214c265e
        System.out.println(student1==student2); // false，student1和student2指向的是不同地址

        System.out.println(student1.getFood()); // JavaPackage_1.Food@6d311334
        System.out.println(student2.getFood()); // JavaPackage_1.Food@448139f0
        System.out.println(student1.getFood()==student2.getFood()); // false，student1.food和student2.food指向不同地址

        food.setFood("orange");
        System.out.println(student1.getFood().getFood()); // orange
        System.out.println(student2.getFood().getFood()); // apple
    }
}
