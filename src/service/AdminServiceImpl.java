package service;

import dao.AdminDao;
import dao.AdminDaoImpl;
import domain.Admin;

public class AdminServiceImpl implements AdminService{

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(String adminName, String password) {
        return adminDao.login(adminName, password);
    }

    @Override
    public boolean register(Admin admin) {
        return adminDao.register(admin);
    }
}
