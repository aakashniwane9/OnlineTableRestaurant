package com.example.eatheaven.Adapter;

public class CartList {

    String name,cost,status,quantity;

    public CartList(String name, String cost, String status, String quantity) {
        this.name = name;
        this.cost = cost;
        this.status = status;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
