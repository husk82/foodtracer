package org.husk.foodtracer;

import java.util.Date;

public class FoodItem {
    private String name;
    private String expirayDate;

    public FoodItem(String name, String expirayDate) {
        this.name = name;
        this.expirayDate = expirayDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpirayDate() {
        return expirayDate;
    }

    public void setExpirayDate(String expirayDate) {
        this.expirayDate = expirayDate;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", expirayDate=" + expirayDate +
                '}';
    }
}
