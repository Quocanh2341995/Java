package models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Borrower implements Serializable {
    private int id;
    private int userId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate expDate;
    private int quantity;
    private BorrowerStatus borrowerStatus;

    public Borrower(int id, int userId, int bookId, LocalDate borrowDate, LocalDate expDate, int quantity, BorrowerStatus borrowerStatus) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.expDate = expDate;
        this.quantity = quantity;
        this.borrowerStatus = borrowerStatus;
    }

    public BorrowerStatus getBorrowerStatus() {
        return borrowerStatus;
    }

    public void setBorrowerStatus(BorrowerStatus borrowerStatus) {
        this.borrowerStatus = borrowerStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Borrower() {
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-7s| %-7s | %-13s | %-13s | %-8s | %-15s |",
                userId ,this.id ,bookId ,borrowDate, expDate, quantity ,borrowerStatus);
    }
}
