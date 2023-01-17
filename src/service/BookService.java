package service;

import domain.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAll();

    public Book findById(int id);

    public boolean deleteBookById(int id);

    public boolean updateBookById(Book book);

    public boolean addBook(Book book);

    // 根据书的类型去查询所有书籍
    public List<Book> findBookByType(int typeId);
    // 根据书的名字去查询所有书籍
    public List<Book> findBookByName(String name);
    // 根据图书名和isbn号查询对应的图书
    public Book findByCardAndName(String bookName, String isbn);
    // 设置图书状态
    public boolean setBookStatus(int status, int id);
}
