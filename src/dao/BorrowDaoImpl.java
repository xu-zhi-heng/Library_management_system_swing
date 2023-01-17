package dao;
import domain.Borrow;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements BorrowDao{

    private Connection connection = DBHelper.getConnection();

    @Override
    public boolean borrowBook(Borrow borrow) {
        String sql = "INSERT INTO borrow(user_id, book_id, borrow_date, is_borrow) VALUES(?, ?, ?, 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, borrow.getUserId());
            ps.setInt(2, borrow.getBookId());
            ps.setDate(3, (Date) borrow.getBorrowDate());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean returnBook(Borrow borrow) {
        String sql = "UPDATE borrow SET is_borrow = 0, return_date = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (Date) borrow.getReturnDate());
            ps.setInt(2, borrow.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Borrow findByUserIdAndBookId(int userId, int BookId) {
        Borrow borrow = null;
        String sql = "SELECT * FROM borrow WHERE user_id = ? AND book_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, BookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                borrow = new Borrow(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getDate(6)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrow;
    }

    @Override
    public List<Borrow> findAll() {
        List<Borrow> list = new ArrayList<Borrow>();
        String sql = "SELECT * FROM borrow";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();// execute-所有sql executeQuery-select executeUpdate-update insert delete
            while (rs.next()) {
                list.add(new Borrow(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getDate(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM borrow WHERE id = ?";
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
    public boolean updateById(Borrow borrow) {
        String sql = "UPDATE borrow SET user_id = ?, book_id = ?, borrow_date = ?, is_borrow = ?, return_date = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, borrow.getUserId());
            ps.setInt(2, borrow.getBookId());
            ps.setDate(3, (Date) borrow.getBorrowDate());
            ps.setInt(4, borrow.getIsBorrow());
            ps.setDate(5, (Date) borrow.getReturnDate());
            ps.setInt(6, borrow.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
