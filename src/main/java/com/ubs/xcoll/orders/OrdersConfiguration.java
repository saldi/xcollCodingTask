package com.ubs.xcoll.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Configuration
public class OrdersConfiguration {

    @Bean
    Supplier<LocalDateTime> orderCreationDateSupplier(){
        return ()->LocalDateTime.now();
    }

}
