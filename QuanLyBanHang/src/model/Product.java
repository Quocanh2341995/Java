package model;

import java.sql.Date;
import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private Category category;
    private Date importDate;

    private Status status;


    public Product() {
    }

    public Product(int id, String name, int quantity, double price, Category category, Date importDate, Status status) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.importDate = importDate;
        this.status = status;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getImportDate() {
        return importDate.toLocalDate();
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = Date.valueOf(importDate);
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + quantity + "," + price + "," + category + "," + importDate + "," + status;
    }
    public String toStringShow(){
        return String.format("| %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |",
                id, name, quantity, price, category, importDate, status);
    }
}
