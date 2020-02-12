package com.ubs.xcoll.alerts.statistics;

import com.ubs.xcoll.alerts.Alert;
import com.ubs.xcoll.alerts.conf.AlertingThresholds;
import com.ubs.xcoll.alerts.AlertsStorage;
import com.ubs.xcoll.orders.AggregatedOrder;
import com.ubs.xcoll.orders.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Component
class DefaultStatisticsAggregator implements StatisticsAggregator {

    private final Map<Product, OrdersAggregator> aggregators;
    private final AlertsStorage alertsStorage;
    private final AlertingThresholds alertingThresholds;
    private final Supplier<LocalDateTime> compareDateSupplier;

    public DefaultStatisticsAggregator(AlertsStorage alertsStorage,
                                       AlertingThresholds alertingThresholds,
                                       Supplier<LocalDateTime> compareDateSupplier) {
        this.alertsStorage = alertsStorage;
        this.alertingThresholds = alertingThresholds;
        this.compareDateSupplier = compareDateSupplier;
        aggregators = new ConcurrentHashMap<>();
    }

    @Override
    public void aggregate(AggregatedOrder order) {
        aggregators.putIfAbsent(order.getProduct(), new OrdersAggregator());
        OrdersAggregator.Statistics statistics = aggregators
                .get(order.getProduct())
                .aggregate(order)
                .countStatistic(compareDateSupplier.get());
        boolean isDailyVolumeBreached = checkDailyVolume(statistics);
        boolean isWeeklyVolumeBreached = checkWeeklyVolume(statistics);
        if (isDailyVolumeBreached || isWeeklyVolumeBreached) {
            alertsStorage.addAlert(new Alert(isWeeklyVolumeBreached, isDailyVolumeBreached, order.getProduct()));
        }
    }

    private boolean checkWeeklyVolume(OrdersAggregator.Statistics statistics) {
        return statistics.getWeeklyVolume() > alertingThresholds.getGetWeeklyVolume();
    }

    private boolean checkDailyVolume(OrdersAggregator.Statistics statistics) {
        return statistics.getDailyVolume() > alertingThresholds.getDailyVolume();
    }
}
