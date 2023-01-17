package service;

import dao.BookDao;
import dao.BookDaoImpl;
import domain.Book;

import java.util.List;

public class BookServiceImpl implements BookService{

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    @Override
    public boolean deleteBookById(int id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public boolean updateBookById(Book book) {
        return bookDao.updateBookById(book);
    }

    @Override
    public boolean addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public List<Book> findBookByType(int typeId) {
        return bookDao.findBookByType(typeId);
    }

    @Override
    public List<Book> findBookByName(String name) {
        return bookDao.findBookByName(name);
    }

    @Override
    public Book findByCardAndName(String bookName, String isbn) {
        return bookDao.findByCardAndName(bookName, isbn);
    }

    @Override
    public boolean setBookStatus(int status, int id) {
        return bookDao.setBookStatus(status, id);
    }
}
