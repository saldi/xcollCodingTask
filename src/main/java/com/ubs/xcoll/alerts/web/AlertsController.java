package com.ubs.xcoll.alerts.web;

import com.ubs.xcoll.alerts.Alert;
import com.ubs.xcoll.alerts.AlertService;
import com.ubs.xcoll.orders.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/alerts")
public class AlertsController {

    private final AlertService alertService;

    public AlertsController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping(value = "/{productName}")
    public Alert getForProduct(@PathVariable("productName") String productName) {
        return alertService.getForProduct(new Product(productName));
    }

    @GetMapping()
    public Collection<Alert> getAll() {
        return alertService.getAll();
    }


}
