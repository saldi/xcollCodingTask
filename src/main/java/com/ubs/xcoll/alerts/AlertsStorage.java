package com.ubs.xcoll.alerts;

import com.ubs.xcoll.orders.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AlertsStorage {

    private Map<Product, Alert> alerts;

    public AlertsStorage() {
        alerts = new ConcurrentHashMap<>();
    }

    public void addAlert(Alert alert) {
        this.alerts.put(alert.getProduct(), alert);
    }

    public Optional<Alert> getAlert(Product product) {
        return Optional.ofNullable(this.alerts.get(product));
    }

    public Collection<Alert> getAll() {
        return alerts.values();
    }
}
