package com.ubs.xcoll.orders;

import java.time.LocalDateTime;

public class AddOrderEvent {

    private final AggregatedOrder aggregatedOrder;

    public AddOrderEvent(Product product, LocalDateTime orderDateTime, int quantity) {
        this.aggregatedOrder = new AggregatedOrder(product, orderDateTime, quantity);
    }

    public AggregatedOrder getAggregatedOrder() {
        return aggregatedOrder;
    }
}
