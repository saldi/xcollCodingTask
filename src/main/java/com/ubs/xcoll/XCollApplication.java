package com.ubs.xcoll;

import com.ubs.xcoll.alerts.conf.AlertingThresholds;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AlertingThresholds.class})
public class XCollApplication {
    public static void main(String[] args) {
        SpringApplication.run(XCollApplication.class, args);
    }
}
