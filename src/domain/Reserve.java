package domain;

import java.util.Date;

public class Reserve {
    private int id;
    private int userId;
    private int bookId;
    private Date reserveTime;
    private int isReserve;
    private Date borrowDate;

    public Reserve(int id, int userId, int bookId, Date reserveTime, int isReserve, Date borrowDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reserveTime = reserveTime;
        this.isReserve = isReserve;
        this.borrowDate = borrowDate;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
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

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public int getIsReserve() {
        return isReserve;
    }

    public void setIsReserve(int isReserve) {
        this.isReserve = isReserve;
    }

    public Reserve() {
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", reserveTime=" + reserveTime +
                ", isReserve=" + isReserve +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
