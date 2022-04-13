package com.example.fdata.entity;

import java.util.HashMap;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private int amount;
    private double price;
    private String details;

    public Product() {
    }

    public Product(String name, int amount, double price,String details) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.details = details;
    }

    public Product(int id, String name, int amount, double price, String details) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.details = details;
    }
    public void checkValid(){
        this.error = new HashMap<>();
        if (this.name==null||this.name.length()==0){
            this.error.put("name","name requied");
        }
        if (this.price==0){
            this.error.put("price","price requied");
        }
    }
    public HashMap<String,String>error;
    public HashMap<String,String>getErrors(){
        checkValid();
        return error;
    }

    public boolean isvalid(){
        checkValid();
        return error ==null||error.size()==0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", price=" + price +
                ", details=" + details +
                '}';
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
