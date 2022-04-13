package com.example.fdata.entity;

public class tbProducts  {

    private int id;
    private String name;
    private double price;
    private String amount;
    private String details;

    public tbProducts() {
    }

    public tbProducts(int id, String name, double price, String amount, String details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.details = details;
    }

    @Override
    public String toString() {
        return "tbProducts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount='" + amount + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
