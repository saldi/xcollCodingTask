package com.ubs.xcoll.alerts.statistics;

import com.ubs.xcoll.orders.AggregatedOrder;

public interface StatisticsAggregator {

    void aggregate(AggregatedOrder order);

}
