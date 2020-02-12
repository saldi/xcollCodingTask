package com.ubs.xcoll.alerts;

import com.ubs.xcoll.alerts.statistics.OrdersAggregator;
import com.ubs.xcoll.orders.AggregatedOrder;
import com.ubs.xcoll.orders.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class OrdersAggregatorTest {

    @Test
    public void testAggregate() {
        OrdersAggregator aggregator = new OrdersAggregator();
        OrdersAggregator.StatisticsBuilder statisticsBuilder =
                aggregator.aggregate(
                        new AggregatedOrder(new Product("product"), LocalDateTime.of(2020, 2, 11, 0, 0), 10));
        OrdersAggregator.Statistics statistics = statisticsBuilder.countStatistic(LocalDateTime.of(2020, 2, 11, 5, 0));
        Assertions.assertThat(statistics.getDailyVolume()).isEqualTo(10);
        OrdersAggregator.Statistics statistics2 = statisticsBuilder.countStatistic(LocalDateTime.of(2020, 2, 14, 0, 0));
        Assertions.assertThat(statistics2.getDailyVolume()).isEqualTo(0);
    }
}