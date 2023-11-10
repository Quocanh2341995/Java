package model;


import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private Date buyDate;
    private OrderStatus orderStatus;

    public Order(int id, int userId, int productId, int quantity, Date buyDate, OrderStatus orderStatus) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.buyDate = buyDate;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getBuyDate() {
        return buyDate.toLocalDate();
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = Date.valueOf(buyDate);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-7s| %-7s | %-13s | %-8s | %-15s |",
                this.id , userId, productId ,buyDate, quantity , orderStatus);
    }
    public String toStringShow() {
        return id + "," + userId + "," + productId + "," + buyDate + "," + quantity + "," + orderStatus;
//        return String.format("| %-7s | %-7s| %-7s | %-13s | %-8s | %-15s |",
//                this.id , userId, productId ,buyDate, quantity , orderStatus);
    }
}
