package com.ubs.xcoll.alerts.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "alert.threshold")
@ConstructorBinding
public class AlertingThresholds {

    private int dailyVolume = 100;
    private int weeklyVolume = 300;

    public AlertingThresholds(int dailyVolume, int weeklyVolume) {
        this.dailyVolume = dailyVolume;
        this.weeklyVolume = weeklyVolume;
    }

    public int getDailyVolume() {
        return dailyVolume;
    }

    public int getGetWeeklyVolume() {
        return weeklyVolume;
    }
}
