package com.ubs.xcoll.alerts.statistics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Configuration
public class StatisticsConfiguration {

    @Bean
    Supplier<LocalDateTime> compareDateSupplier() {
        return LocalDateTime::now;
    }

}
