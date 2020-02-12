package com.ubs.xcoll.alerts;

import com.ubs.xcoll.orders.Product;

import java.util.Collection;

public interface AlertService {

    Collection<Alert> getAll();

    Alert getForProduct(Product product);

}
