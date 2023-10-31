package model;

import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private String descript;
    private BigDecimal price;

    public Product(int id, String name, String descript, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.descript = descript;
        this.price = price;
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

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
