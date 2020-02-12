package com.ubs.xcoll.alerts;

import com.ubs.xcoll.alerts.statistics.StatisticsAggregator;
import com.ubs.xcoll.orders.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
class DefaultAlertService implements AlertService {

    private final StatisticsAggregator statisticsAggregator;
    private final AlertsStorage alertsStorage;

    public DefaultAlertService(StatisticsAggregator statisticsAggregator, AlertsStorage alertsStorage) {
        this.statisticsAggregator = statisticsAggregator;
        this.alertsStorage = alertsStorage;
    }

    @Override
    public Collection<Alert> getAll() {
        return alertsStorage.getAll();
    }

    @Override
    public Alert getForProduct(Product product) {
        return alertsStorage.getAlert(product).orElse(Alert.buildEmptyAlert(product));
    }

}
