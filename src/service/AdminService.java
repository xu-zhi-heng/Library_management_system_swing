package service;

import domain.Admin;

public interface AdminService {
    public Admin login(String adminName, String password);
    public boolean register(Admin admin);
}
