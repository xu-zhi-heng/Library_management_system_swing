package dao;

import domain.BookType;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookTypeDaoImpl implements BookTypeDao{

    private Connection connection = DBHelper.getConnection();

    @Override
    public List<BookType> findAll() {
        List<BookType> list = new ArrayList<BookType>();
        String sql = "SELECT * FROM book_type";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new BookType(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addBookType(BookType bookType) {
        String sql = "INSERT INTO book_type(type_name) VALUES( ? )";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookType.getTypeName());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBookTypeById(int id) {
        String sql = "DELETE FROM book_type WHERE id = ?";
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
    public boolean updateBookType(BookType bookType) {
        String sql = "UPDATE book_type SET type_name = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bookType.getTypeName());
            ps.setInt(2, bookType.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BookType findById(int id) {
        String sql = "select * from book_type where id = ?";
        BookType bookType = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                bookType = new BookType(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookType;
    }

    @Override
    public BookType findByName(String typeName) {
        String sql = "select * from book_type where type_name = ?";
        BookType bookType = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, typeName.trim());
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                bookType = new BookType(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookType;
    }
}
