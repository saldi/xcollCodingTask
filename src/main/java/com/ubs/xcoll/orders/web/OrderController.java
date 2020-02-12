package com.ubs.xcoll.orders.web;

import com.ubs.xcoll.orders.Order;
import com.ubs.xcoll.orders.OrderItem;
import com.ubs.xcoll.orders.OrderService;
import com.ubs.xcoll.orders.Product;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final Supplier<LocalDateTime> orderCreationDateSupplier;

    public OrderController(OrderService orderService, Supplier<LocalDateTime> orderCreationDateSupplier) {
        this.orderService = orderService;
        this.orderCreationDateSupplier = orderCreationDateSupplier;
    }

    @PostMapping
    public void addOrder(@RequestBody List<RequestOrderItem> orderItems) {
        this.orderService.createOrder(Order.of(orderItems
                .stream()
                .map(orderItem -> OrderItem.from(orderItem.getProduct(), orderItem.getQuantity()))
                .collect(Collectors.toList()), orderCreationDateSupplier.get()));
    }

    @GetMapping(value = "/{productName}", params = "_count")
    public int getOrderedQuantity(@PathVariable("productName") String productName) {
        return this.orderService.getOrderedQuantity(new Product(productName));
    }

}
