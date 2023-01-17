package view;
import domain.Admin;
import service.AdminService;
import service.AdminServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JDialog {

    private AdminService adminService = new AdminServiceImpl();

    public JPanel jPanel = new JPanel();
    public JLabel usernameLabel = new JLabel("用户名:", JLabel.LEFT);
    public JTextField usernameText = new JTextField(12);
    public JLabel passwordLabel = new JLabel("密码:", JLabel.LEFT);
    public JPasswordField passwordText = new JPasswordField(12);
    public JButton registerBtn = new JButton("注册");

    public RegisterView(LoginView loginView) {
        super(loginView, "注册页面", true);

        jPanel.setLayout(null);
        jPanel.setBackground(new Color(0xe7fafe));

        usernameLabel.setForeground(new Color(0xa5d478));
        usernameLabel.setFont(new Font("黑体", Font.PLAIN, 30));
        usernameLabel.setBounds(100, 80, 200, 40);
        jPanel.add(usernameLabel);


        usernameText.setFont(new Font("黑体", Font.PLAIN, 18));
        usernameText.setBounds(230, 80, 280, 40);
        jPanel.add(usernameText);


        passwordLabel.setForeground(new Color(0xa5d478));
        passwordLabel.setFont(new Font("黑体", Font.PLAIN, 30));
        passwordLabel.setBounds(100, 150, 200, 40);
        jPanel.add(passwordLabel);

        //密码框
        passwordText.setBounds(230, 150, 280, 40);
        jPanel.add(passwordText);

        registerBtn.setForeground(new Color(0xa5d478));
        registerBtn.setBackground(new Color(0xfffed5));
        registerBtn.setFont(new Font("黑体", Font.PLAIN, 20));
        registerBtn.setBorderPainted(false);
        registerBtn.setBounds(270, 220, 80, 40);
        jPanel.add(registerBtn);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminName = usernameText.getText().trim();
                String adminPassword = passwordText.getText().trim();
                if (adminName.equals("") || adminPassword.equals("")) {
                    JOptionPane.showMessageDialog(null,"用户名或者密码不能为空","请重试", JOptionPane.ERROR_MESSAGE);
                } else {
                    Admin admin = new Admin();
                    admin.setAdminName(adminName);
                    admin.setAdminPassword(adminPassword);
                    boolean register = adminService.register(admin);
                    if (register) {
                        JOptionPane.showMessageDialog(null,"注册成功，请登录","成功", 1);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,"注册失败","失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        add(jPanel);
        setTitle("注册页面");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
