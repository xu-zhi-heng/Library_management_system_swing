package service;

import dao.BookTypeDao;
import dao.BookTypeDaoImpl;
import domain.BookType;

import java.util.List;

public class BookTypeServiceImpl implements BookTypeService{

    private BookTypeDao bookTypeDao = new BookTypeDaoImpl();
    @Override
    public List<BookType> findAll() {
        return bookTypeDao.findAll();
    }

    @Override
    public boolean addBookType(BookType bookType) {
        return bookTypeDao.addBookType(bookType);
    }

    @Override
    public boolean deleteBookTypeById(int id) {
        return bookTypeDao.deleteBookTypeById(id);
    }

    @Override
    public boolean updateBookType(BookType bookType) {
        return bookTypeDao.updateBookType(bookType);
    }

    @Override
    public BookType findById(int id) {
        return bookTypeDao.findById(id);
    }

    @Override
    public BookType findByName(String typeName) {
        return bookTypeDao.findByName(typeName);
    }
}
