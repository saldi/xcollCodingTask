package com.ubs.xcoll.orders;

import java.time.LocalDateTime;

public class AggregatedOrder {
    private final Product product;
    private final LocalDateTime dateTime;
    private final int quantity;

    public AggregatedOrder(Product product, LocalDateTime dateTime, int quantity) {
        this.product = product;
        this.dateTime = dateTime;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getQuantity() {
        return quantity;
    }
}
