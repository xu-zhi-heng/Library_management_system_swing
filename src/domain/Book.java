package domain;

public class Book {
    private int id;
    private String isbn;
    private String bookName;
    private String author;
    private int isBorrow;
    private int bookType;

    public Book(int id, String isbn, String bookName, String author, int isBorrow, int bookType) {
        this.id = id;
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.isBorrow = isBorrow;
        this.bookType = bookType;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(int isBorrow) {
        this.isBorrow = isBorrow;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", isBorrow=" + isBorrow +
                ", bookType=" + bookType +
                '}';
    }
}
