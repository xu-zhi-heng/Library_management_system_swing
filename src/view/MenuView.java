package view;
import view.ActionView.*;
import view.BookTypeView.BookTypeManageView;
import view.BookView.BookManageView;
import view.UserView.UserManageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

// 首页，菜单页面
public class MenuView extends JFrame {

    public static void main(String[] args) {
        new MenuView();
    }

    private JMenuBar menuBar;
    private JMenu bookManage, userManage, bookTypeManage, actionManage;
    // 图书书刊操作
    private JMenuItem allBookItem;
    // 图书书种操作
    private JMenuItem allBookType;
    // 用户操作
    private JMenuItem exitItem, allUser;
    // 基本业务
    private JMenuItem borrowBookItem, returnBookItem, bookBookItem;
    // 借书管理，预定管理
    private JMenuItem allBorrows, allReserves;
    private Object object = this;

    public MenuView() {
        init();
        setTitle("SweetFun图书管理系统");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    void init() {
        menuBar = new JMenuBar();
        initBookManage();
        initUserManage();
        initBookTypeManage();
        initOptions();
        initMenuBar();

        URL imgUrl = MenuView.class.getClassLoader().getResource("./img/main.jpg");
        ImageIcon img = new ImageIcon(imgUrl);
        JLabel background = new JLabel(img);
        background.setSize(800, 600);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, 800, 600);

        JPanel jPanel = (JPanel) this.getContentPane();
        jPanel.setOpaque(false);
        jPanel.setLayout(new FlowLayout());

        JLabel titleLabel = new JLabel("欢迎使用SweetFun图书管理系统");
        titleLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
        titleLabel.setForeground(new Color(0xDE0742));
        jPanel.add(titleLabel);
    }


    public void initBookManage() {
        // 图书管理
        bookManage = new JMenu("图书管理");
        allBookItem = new JMenuItem("所有图书");
        bookManage.add(allBookItem);
        allBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookManageView((MenuView) object);
            }
        });
    }

    public void initUserManage() {
        // 用户管理菜单项
        userManage = new JMenu("用户管理");
        allUser = new JMenuItem("所有用户");
        userManage.add(allUser);
        allUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserManageView((MenuView) object);
            }
        });
    }

    public void initBookTypeManage() {
        // 图书类别管理
        bookTypeManage = new JMenu("图书类别管理");
        allBookType = new JMenuItem("所有图书类别");
        bookTypeManage.add(allBookType);
        allBookType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookTypeManageView((MenuView) object);
            }
        });
    }

    public void initOptions() {
        // 操作模块
        actionManage = new JMenu("功能选项");
        borrowBookItem = new JMenuItem("借书");
        returnBookItem = new JMenuItem("还书");
        bookBookItem = new JMenuItem("预定");
        actionManage.add(borrowBookItem);
        actionManage.add(returnBookItem);
        actionManage.add(bookBookItem);


        // 借书
        borrowBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrowBookView((MenuView) object);
            }
        });

        // 还书
        returnBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReturnBookView((MenuView) object);
            }
        });

        // 预定书
        bookBookItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReserveBookView((MenuView) object);
            }
        });

        allBorrows = new JMenuItem("借书记录");
        allReserves = new JMenuItem("预定记录");
        actionManage.add(allBorrows);
        actionManage.add(allReserves);
        exitItem = new JMenuItem("退出系统");
        actionManage.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        allBorrows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrowManageView((MenuView) object);
            }
        });

        allReserves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResManageView((MenuView) object);
            }
        });
    }


    public void initMenuBar() {
        menuBar.add(actionManage);
        menuBar.add(userManage);
        menuBar.add(bookManage);
        menuBar.add(bookTypeManage);
        setJMenuBar(menuBar);
    }

}
