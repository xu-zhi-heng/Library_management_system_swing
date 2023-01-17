package dao;

import domain.Book;
import domain.BookType;

import java.util.List;

public interface BookTypeDao {
    public List<BookType> findAll();
    public boolean addBookType(BookType bookType);
    public boolean deleteBookTypeById(int id);
    public boolean updateBookType(BookType bookType);
    public BookType findById(int id);
    public BookType findByName(String typeName);
}
