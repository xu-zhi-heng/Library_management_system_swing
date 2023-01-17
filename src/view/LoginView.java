package view;

import controller.LoginListen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class LoginView extends JFrame{

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
    }

    public JLabel titleLabel = new JLabel("图书管理系统", JLabel.CENTER);
    public SpringLayout springLayout = new SpringLayout();
    public JPanel centerPanel = new JPanel(springLayout);

    public JLabel adminNameLabel = new JLabel("管理员用户名：");
    public JLabel adminPasswordLabel = new JLabel("管理员密码：");
    public JTextField nameText = new JTextField();
    public JTextField passwordText = new JPasswordField();

    public JButton loginBtn = new JButton("登录");
    public JButton registerBtn = new JButton("注册");

    public LoginView() {
        Container container = getContentPane();

        // 设置标题字体
        titleLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
        titleLabel.setPreferredSize(new Dimension(0, 80));

        // 中间区域字体
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        adminNameLabel.setFont(centerFont);
        adminPasswordLabel.setFont(centerFont);
        nameText.setFont(centerFont);
        passwordText.setFont(centerFont);
        loginBtn.setFont(centerFont);
        registerBtn.setFont(centerFont);

        // 设置输入框大小
        nameText.setPreferredSize(new Dimension(200, 30));
        passwordText.setPreferredSize(new Dimension(200, 30));

        // 将组件添加到面板中
        centerPanel.add(adminNameLabel);
        centerPanel.add(adminPasswordLabel);
        centerPanel.add(nameText);
        centerPanel.add(passwordText);
        centerPanel.add(loginBtn);
        centerPanel.add(registerBtn);

        // 添加事件
        LoginListen loginListen = new LoginListen();
        loginListen.setLoginView(this);
        loginBtn.addActionListener(loginListen);

        // 注册操作
        Object object = this;
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterView((LoginView) object);
            }
        });

        // 组件布局
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(adminNameLabel), Spring.width(nameText)), Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        // adminNameLabel
        springLayout.putConstraint(SpringLayout.WEST, adminNameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, adminNameLabel, 20, SpringLayout.NORTH, centerPanel);

        // nameText
        springLayout.putConstraint(SpringLayout.WEST, nameText, 20, SpringLayout.EAST, adminNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameText, 0, SpringLayout.NORTH, adminNameLabel);

        // adminPasswordLabel
        springLayout.putConstraint(SpringLayout.EAST, adminPasswordLabel, 0, SpringLayout.EAST, adminNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, adminPasswordLabel, 20, SpringLayout.SOUTH, adminNameLabel);

        // passwordText
        springLayout.putConstraint(SpringLayout.WEST, passwordText, 20, SpringLayout.EAST, adminPasswordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordText, 0, SpringLayout.NORTH, adminPasswordLabel);

        // 登录按钮
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 50, SpringLayout.WEST, adminPasswordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 20, SpringLayout.SOUTH, adminPasswordLabel);

        // 注册按钮
        springLayout.putConstraint(SpringLayout.WEST, registerBtn, 60, SpringLayout.EAST, loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH, registerBtn, 0, SpringLayout.NORTH, loginBtn);

        // 设置内容布局
        container.add(titleLabel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);

        // 自定义图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("./img/橙子.png");
        setIconImage(new ImageIcon(imgUrl).getImage());

        setTitle("SweetFun图书管理系统");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
