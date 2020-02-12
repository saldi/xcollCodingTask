package com.ubs.xcoll.orders;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class DefaultOrderService implements OrderService {

    private final ApplicationEventPublisher publisher;

    private final Map<Product, Integer> ordersQuantity;

    public DefaultOrderService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.ordersQuantity = new ConcurrentHashMap<>();
    }

    @Override
    public void createOrder(Order order) {
        order.getItems().forEach(item -> {
                    this.ordersQuantity.compute(item.getProduct(), (product, oldValue) ->
                            oldValue == null
                                    ? Integer.valueOf(item.getQuantity())
                                    : Integer.valueOf(oldValue + item.getQuantity()));
                    publisher.publishEvent(new AddOrderEvent(item.getProduct(), order.getCreationDate(), item.getQuantity()));
                }
        );
    }

    @Override
    public int getOrderedQuantity(Product product) {
        return ordersQuantity.getOrDefault(product, 0);
    }
}
