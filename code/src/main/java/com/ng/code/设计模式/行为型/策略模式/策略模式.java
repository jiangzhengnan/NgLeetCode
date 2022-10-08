package com.ng.code.设计模式.行为型.策略模式;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiangzhengnan.jzn@alibaba-inc.com
 * @creation : 2022/10/05
 * @description :
 * 概念：
 * 定义一族算法类，将每个算法分别封装起来，让它们可以互相替换。
 * 使算法的变化独立于使用算法的代码。
 * 示例：
 * 有普通订单和团购订单两种，分别对应不同降价计算规则，需要在拿到订单时自动根据降价策略计算价格。
 */
public class 策略模式 {

    /**
     * 订单
     */
    static class Order {
        public OrderType mOrderType;
        public double price;

        public Order(final OrderType orderType, final double price) {
            mOrderType = orderType;
            this.price = price;
        }
    }

    /**
     * 订单类型
     */
    enum OrderType {
        NORMAL, GROUPON,
    }

    /**
     * 策略的定义,降价策略
     */
    public interface DiscountStrategy {
        double calDiscount(Order order);
    }

    /**
     * 普通降价策略
     */
    static class NormalDiscountStrategy implements DiscountStrategy {
        @Override
        public double calDiscount(final Order order) {
            return order.price * 1.0d;
        }
    }

    /**
     * 团购降价策略
     */
    static class GrouponDiscountStrategy implements DiscountStrategy {
        @Override
        public double calDiscount(final Order order) {
            return order.price * 0.8d;
        }
    }

    // 策略的创建
    static class DiscountStrategyFactory {
        private static final Map<OrderType, DiscountStrategy> strategies = new HashMap<>();

        static {
            strategies.put(OrderType.NORMAL, new NormalDiscountStrategy());
            strategies.put(OrderType.GROUPON, new GrouponDiscountStrategy());
        }

        public static DiscountStrategy getDiscountStrategy(OrderType type) {
            return strategies.get(type);
        }
    }

    // 策略的使用,订单管理
    static class OrderService {
        public double discount(Order order) {
            OrderType type = order.mOrderType;
            DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(type);
            return discountStrategy.calDiscount(order);
        }
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        System.out.println("普通价格:" + orderService.discount(new Order(OrderType.NORMAL, 100d)));
        System.out.println("团购价格:" + orderService.discount(new Order(OrderType.GROUPON, 100d)));
    }

}
