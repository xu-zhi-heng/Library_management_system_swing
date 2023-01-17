package dao;

import domain.Book;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{

    private Connection connection = DBHelper.getConnection();

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<Book>();
        String sql = "SELECT * FROM book";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Book findById(int id) {
        String sql = "select * from book where id = ?";
        Book book = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                book = new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean deleteBookById(int id) {
        String sql = "DELETE FROM book WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBookById(Book book) {
        String sql = "UPDATE book SET isbn = ?, book_name = ?, author = ?, is_borrow = ?, book_type = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getIsBorrow());
            ps.setInt(5, book.getBookType());
            ps.setInt(6, book.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addBook(Book book) {
        String sql = "INSERT INTO book(isbn, book_name, author, is_borrow, book_type) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getBookName());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getIsBorrow());
            ps.setInt(5, book.getBookType());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> findBookByType(int typeId) {
        String sql = "select * from book where book_type = ?";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, typeId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Book> findBookByName(String name) {
        String sql = "select * from book where book_name like ?";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Book findByCardAndName(String bookName, String isbn) {
        String sql = "select * from book where book_name = ? and isbn = ?";
        Book book = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookName);
            ps.setString(2, isbn);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                book = new Book(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean setBookStatus(int status, int id) {
        String sql = "UPDATE book SET is_borrow = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
