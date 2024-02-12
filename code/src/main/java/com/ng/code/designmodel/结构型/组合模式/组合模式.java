package com.ng.code.designmodel.结构型.组合模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 
 * @creation : 2022/10/04
 * @description :
 * 概念:
 * 将一组对象组织（Compose）成树形结构，以表示一种“部分-整体”的层次结构。
 * 组合让客户端（在很多设计模式书籍中，“客户端”代指代码的使用者。）可以统一单个对象和组合对象的处理逻辑。
 * 示例：
 * 希望在内存中构建整个公司的人员架构图（部门、子部门、员工的隶属关系），
 * 并且提供接口计算出部门的薪资成本（隶属于这个部门的所有员工的薪资和）。
 * 采用组合的方式可以更方便的处理员工与部门的工资计算。
 */
public class 组合模式 {

    //人力资源
    static abstract class HumanResource {
        protected long id;
        protected double salary;

        public HumanResource(long id) {
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public abstract double calculateSalary();
    }

    //员工
    static class Employee extends HumanResource {
        public Employee(long id, double salary) {
            super(id);
            this.salary = salary;
        }

        @Override
        public double calculateSalary() {
            return salary;
        }
    }

    //部门
    public static class Department extends HumanResource {
        private List<HumanResource> subNodes = new ArrayList<>();

        public Department(long id) {
            super(id);
        }

        @Override
        public double calculateSalary() {
            double totalSalary = 0;
            for (HumanResource hr : subNodes) {
                totalSalary += hr.calculateSalary();
            }
            this.salary = totalSalary;
            return totalSalary;
        }

        public void addSubNode(HumanResource hr) {
            subNodes.add(hr);
        }
    }

    public static void main(String[] args) {
        //it部门
        Department itDepartment = new Department(1);
        itDepartment.addSubNode(new Employee(1, 5000));
        itDepartment.addSubNode(new Employee(2, 5100));
        itDepartment.addSubNode(new Employee(3, 5200));
        itDepartment.addSubNode(new Employee(4, 5300));
        itDepartment.addSubNode(new Employee(5, 5400));
        //客户部门
        Department customerDepartment = new Department(1);
        customerDepartment.addSubNode(new Employee(1, 1000));
        customerDepartment.addSubNode(new Employee(2, 1100));
        customerDepartment.addSubNode(new Employee(3, 1200));
        customerDepartment.addSubNode(new Employee(4, 1300));
        customerDepartment.addSubNode(new Employee(5, 1400));

        //总薪水
        double salaryAll = itDepartment.calculateSalary() + customerDepartment.calculateSalary();
        System.out.println("总薪水:" + salaryAll);
    }

}
