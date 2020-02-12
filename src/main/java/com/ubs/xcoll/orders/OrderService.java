package com.ubs.xcoll.orders;

public interface OrderService {

    void createOrder(Order order);

    int getOrderedQuantity(Product product);
}
