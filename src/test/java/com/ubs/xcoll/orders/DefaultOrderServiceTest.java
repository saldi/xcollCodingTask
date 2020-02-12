package com.ubs.xcoll.orders;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class DefaultOrderServiceTest {

    private final Supplier<LocalDateTime> orderCreationDateSupplier = LocalDateTime::now;

    @Test
    public void testCreateOrder() {
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        OrderService orderService = new DefaultOrderService(publisher);
        orderService.createOrder(simpleOrder());
        Assertions.assertThat(orderService.getOrderedQuantity(new Product("product"))).isEqualTo(1);
    }

    @Test
    public void testCreateMultiItemOrder() {
        ApplicationEventPublisher publisher = Mockito.mock(ApplicationEventPublisher.class);
        OrderService orderService = new DefaultOrderService(publisher);
        orderService.createOrder(multiItemOrder());
        Assertions.assertThat(orderService.getOrderedQuantity(new Product("product"))).isEqualTo(3);
    }

    private Order simpleOrder() {
        OrderItem item = OrderItem.from("product", 1);
        return Order.of(Lists.newArrayList(item), orderCreationDateSupplier.get());
    }

    private Order multiItemOrder() {
        OrderItem item1 = OrderItem.from("product", 1);
        OrderItem item2 = OrderItem.from("product", 1);
        OrderItem item3 = OrderItem.from("product", 1);
        return Order.of(Lists.newArrayList(item1, item2, item3), orderCreationDateSupplier.get());
    }

}