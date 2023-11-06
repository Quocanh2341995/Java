package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private String descript;
    private BigDecimal price;
    private Category category;
    private int categoryId;
    private Date importDate;


    public Product(int id, String name, String descript, BigDecimal price, int categoryId, Date importDate) {
        this.id = id;
        this.name = name;
        this.descript = descript;
        this.price = price;
        this.categoryId = categoryId;
        this.importDate = importDate;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + descript + "," + price + "," + categoryId + "," + importDate;
    }
}
