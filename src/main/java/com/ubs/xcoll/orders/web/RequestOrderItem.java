package com.ubs.xcoll.orders.web;

public class RequestOrderItem {

    private final String product;
    private final int quantity;

    public RequestOrderItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
