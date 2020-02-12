package com.ubs.xcoll.alerts;

import com.ubs.xcoll.orders.Product;

public class Alert {
    private final boolean weeklyVolumeBreached;
    private final boolean dailyVolumeBreached;
    private final Product product;

    public Alert(boolean weeklyVolumeBreached, boolean dailyVolumeBreached, Product product) {
        this.weeklyVolumeBreached = weeklyVolumeBreached;
        this.dailyVolumeBreached = dailyVolumeBreached;
        this.product = product;
    }

    public boolean isWeeklyVolumeBreached() {
        return weeklyVolumeBreached;
    }

    public boolean isDailyVolumeBreached() {
        return dailyVolumeBreached;
    }

    public Product getProduct() {
        return product;
    }


    public static Alert buildEmptyAlert(Product product){
        return new Alert(false, false, product);
    }
}
