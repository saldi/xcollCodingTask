package com.ubs.xcoll.alerts;

import com.ubs.xcoll.alerts.statistics.StatisticsAggregator;
import com.ubs.xcoll.orders.AddOrderEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class OnAddOrderListener {
    private final StatisticsAggregator statisticsAggregator;

    public OnAddOrderListener(StatisticsAggregator statisticsAggregator) {
        this.statisticsAggregator = statisticsAggregator;
    }

    @EventListener
    public void onAdd(AddOrderEvent event) {
        this.statisticsAggregator.aggregate(event.getAggregatedOrder());
    }

}
