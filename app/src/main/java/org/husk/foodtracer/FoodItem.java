package org.husk.foodtracer;

import java.util.Date;

public class FoodItem {
    private String name;
    private String expiryDate;
    private int quantity;

    public FoodItem(String name, String expiryDate, int quantity) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", numberOfItems=" + quantity +
                '}';
    }

}
