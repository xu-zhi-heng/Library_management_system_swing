package dao;

import domain.Admin;
import domain.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoImpl implements AdminDao{

    private Connection connection = DBHelper.getConnection();

    @Override
    public Admin login(String adminName, String password) {
        Admin admin = null;
        String sql = "SELECT * FROM root WHERE admin_name = ? AND admin_password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, adminName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean register(Admin admin) {
        String sql = "INSERT INTO root(admin_name, admin_password) VALUES(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, admin.getAdminName());
            ps.setString(2, admin.getAdminPassword());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
