package dao;

import domain.Admin;

public interface AdminDao {
    public Admin login(String adminName, String password);
    public boolean register(Admin admin);
}
