package com.example.eatheaven.Adapter;

public class TrackFoodList {
    String name,quantity,status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TrackFoodList(String name, String quantity, String status) {
        this.name = name;
        this.quantity = quantity;
        this.status = status;
    }
}
