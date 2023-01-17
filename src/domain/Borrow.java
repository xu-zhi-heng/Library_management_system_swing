package domain;

import java.util.Date;

public class Borrow {
    private int id;
    private int userId;
    private int bookId;
    private Date borrowDate;
    private int isBorrow;
    private Date returnDate;

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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(int isBorrow) {
        this.isBorrow = isBorrow;
    }

    public Borrow(int id, int userId, int bookId, Date borrowDate, int isBorrow, Date returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.isBorrow = isBorrow;
        this.returnDate = returnDate;
    }

    public Borrow() {
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
