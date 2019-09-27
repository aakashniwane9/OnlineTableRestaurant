package com.example.eatheaven.Adapter;

public class card_recycler {

    String name,cost,type,key;

    public card_recycler(String name, String cost, String type,String key) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.key= key;
    }

    public String getKey(){
        return key;
    }
    public void setKey(){}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
