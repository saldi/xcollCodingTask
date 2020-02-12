package com.ubs.xcoll.orders;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Order {
    private final Collection<OrderItem> items;
    private final LocalDateTime creationDate;

    private Order(Collection<OrderItem> items, LocalDateTime creationDate) {
        this.items = items;
        this.creationDate = creationDate;
    }

    public Collection<OrderItem> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public static Order of(List<OrderItem> items, LocalDateTime creationDate) {
        return new Order(items, creationDate);
    }

}
