package controller;

import domain.Admin;
import domain.User;
import service.AdminService;
import service.AdminServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import view.LoginView;
import view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListen implements ActionListener {
    private LoginView loginView;
    private AdminService adminService = new AdminServiceImpl();

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = loginView.nameText.getText();
        String password = loginView.passwordText.getText();
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null,"用户名或密码不能为空","提醒", JOptionPane.WARNING_MESSAGE);
        } else {
            Admin admin = adminService.login(username, password);
            if (admin == null) {
                JOptionPane.showMessageDialog(null,"用户不存在或密码错误","请重试", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"登录成功","成功", JOptionPane.CANCEL_OPTION);
                loginView.dispose();
                new MenuView();
            }
        }
    }
}
