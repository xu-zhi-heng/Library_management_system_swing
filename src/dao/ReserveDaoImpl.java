package dao;

import domain.Reserve;
import domain.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReserveDaoImpl implements ReserveDao{

    private Connection connection = DBHelper.getConnection();

    @Override
    public boolean reserveBook(Reserve reserve) {
        String sql = "INSERT INTO reserve(user_id, book_id, reserve_time, is_reserve) VALUES(?, ?, ?, 0)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, reserve.getUserId());
            ps.setInt(2, reserve.getBookId());
            ps.setDate(3, (Date) reserve.getReserveTime());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReserve(Reserve reserve) {
        String sql = "UPDATE reserve SET is_reserve = 1, borrow_date = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (Date) reserve.getBorrowDate());
            ps.setInt(2, reserve.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Reserve> findAll() {
        List<Reserve> list = new ArrayList<Reserve>();
        String sql = "SELECT * FROM reserve";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reserve(
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
        String sql = "DELETE FROM reserve WHERE id = ?";
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
    public List<Reserve> findByBookId(int bookId) {
        List<Reserve> list = new ArrayList<Reserve>();
        String sql = "SELECT * FROM reserve where book_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Reserve(
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
}
