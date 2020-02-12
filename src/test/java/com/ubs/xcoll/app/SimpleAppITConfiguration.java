package com.ubs.xcoll.app;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@TestConfiguration
public class SimpleAppITConfiguration {

    @Bean
    Supplier<LocalDateTime> compareDateSupplier() {
        return () -> LocalDateTime.of(2020, 2, 10, 0, 0);
    }

    @Bean
    Supplier<LocalDateTime> orderCreationDateSupplier(){
        return ()->LocalDateTime.of(2020, 2, 9, 10, 0);
    }
}
