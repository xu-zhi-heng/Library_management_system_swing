package dao;

import domain.Book;

import java.util.List;

public interface BookDao {
    public List<Book> findAll();

    public Book findById(int id);

    public boolean deleteBookById(int id);

    public boolean updateBookById(Book book);

    public boolean addBook(Book book);

    // 根据书的类型去查询所有书籍
    public List<Book> findBookByType(int typeId);
    // 根据书的名字去查询所有书籍
    public List<Book> findBookByName(String name);
    public Book findByCardAndName(String bookName, String isbn);
    public boolean setBookStatus(int status, int id);
}
