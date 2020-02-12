package com.ubs.xcoll.orders;

public class OrderItem {

    private final Product product;
    private final int quantity;

    private OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public static OrderItem from(String product, int quantity) {
        return new OrderItem(new Product(product), quantity);
    }
}
