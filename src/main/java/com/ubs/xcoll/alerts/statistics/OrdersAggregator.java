package com.ubs.xcoll.alerts.statistics;

import com.google.common.base.Suppliers;
import com.ubs.xcoll.orders.AggregatedOrder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class OrdersAggregator {

    private final Lock lock;
    private final Queue<TreeElement> orders;

    public OrdersAggregator() {
        this.lock = new ReentrantLock();
        orders = new PriorityQueue<>();
    }

    public StatisticsBuilder aggregate(AggregatedOrder aggregatedOrder) {
        try {
            lock.lock();
            addOrder(aggregatedOrder);
            normalizeOrders();
            return new StatisticsBuilder();
        } finally {
            lock.unlock();
        }
    }

    private void normalizeOrders() {
        LocalDateTime time = LocalDateTime.now();
        Iterator<TreeElement> iterator = orders.iterator();
        while (iterator.hasNext()) {
            TreeElement nextOrder = iterator.next();
            if (nextOrder.orderTime.isBefore(time.minusHours(24 * 7)))
                iterator.remove();
            else
                break;
        }
    }

    private int countOrders(LocalDateTime time, int hours) {
        int quantity = 0;
        Iterator<TreeElement> iterator = orders.iterator();
        while (iterator.hasNext()) {
            TreeElement nextOrder = iterator.next();
            if (nextOrder.orderTime.isAfter(time.minusHours(hours)))
                quantity = quantity + nextOrder.quantity;
            else
                break;
        }
        return quantity;
    }

    private void addOrder(AggregatedOrder aggregatedOrder) {
        this.orders.add(new TreeElement(aggregatedOrder.getDateTime(), aggregatedOrder.getQuantity()));
    }

    static class TreeElement implements Comparable<TreeElement> {

        private final LocalDateTime orderTime;
        private final int quantity;

        TreeElement(LocalDateTime orderTime, int quantity) {
            this.orderTime = orderTime;
            this.quantity = quantity;
        }

        public LocalDateTime getOrderTime() {
            return orderTime;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public int compareTo(TreeElement o) {
            return this.orderTime.compareTo(o.orderTime);
        }
    }

    public class Statistics {
        private final int dailyVolume;
        private final int weeklyVolume;

        public Statistics(int dailyVolume, int weeklyVolume) {
            this.dailyVolume = dailyVolume;
            this.weeklyVolume = weeklyVolume;
        }

        public int getDailyVolume() {
            return dailyVolume;
        }

        public int getWeeklyVolume() {
            return weeklyVolume;
        }
    }

    public class StatisticsBuilder {
        public Statistics countStatistic(LocalDateTime from) {
            int daily = OrdersAggregator.this.countOrders(from, 24);
            int weekly = OrdersAggregator.this.countOrders(from, 24 * 7);
            return new Statistics(daily, weekly);
        }
    }

}
