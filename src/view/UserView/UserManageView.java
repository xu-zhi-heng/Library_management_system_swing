package view.UserView;
import domain.User;
import jdk.nashorn.internal.scripts.JD;
import service.*;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserManageView extends JDialog {
    private UserService userService = new UserServiceImpl();
    public JButton deleteButton = new JButton("删除用户");
    public JButton updateButton = new JButton("更新用户");
    public JButton addButton = new JButton("添加用户");
    public JButton addCardButton = new JButton("添加借阅卡号");

    public JLabel idLabel = new JLabel("用户ID:");
    public JTextField idText = new JTextField();

    public JPanel jPanel = new JPanel();

    public Object[][] rowData = null;
    public String[] cNames = {"主键", "用户名", "密码", "借阅卡号", "性别", "年龄"};

    public UserManageView(MenuView menuView) {

        super(menuView, "用户管理", true);

        init(menuView);
        setTitle("用户管理管理");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new BorderLayout());
    }

    public void init(MenuView menuView) {
        getData();
        JTable table = new JTable(rowData, cNames);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        idLabel.setPreferredSize(new Dimension(40, 30));
        idText.setPreferredSize(new Dimension(100, 30));
        jPanel.add(idLabel);
        jPanel.add(idText);
        jPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idText.getText().equals("")) {
                    int id = Integer.parseInt(idText.getText());
                    boolean b = userService.deleteUserById(id);
                    if (b) {
                        JOptionPane.showMessageDialog(null,"删除用户成功","成功", 1);
                        dispose();
                        new UserManageView(menuView);
                    } else {
                        JOptionPane.showMessageDialog(null,"删除用户失败","请重试", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"请输入ID","请重试", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jPanel.add(updateButton);
        Object object = this;
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idText.getText().equals("")) {
                    int id = Integer.parseInt(idText.getText());
                    User user = userService.findUserById(id);
                    new UpdateUserView((UserManageView) object, user, menuView);
                } else {
                    JOptionPane.showMessageDialog(null,"请输入ID","请重试", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserView((UserManageView) object, menuView);
            }
        });

        jPanel.add(addCardButton);
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idText.getText().equals("")) {
                    String card = JOptionPane.showInputDialog(null,"请输入借阅者卡号","输入数据", 1);
                    int id = Integer.parseInt(idText.getText());
                    User updateUser = new User();
                    updateUser.setCard(card);
                    updateUser.setId(id);
                    boolean b = userService.addCard(updateUser);
                    if (b) {
                        JOptionPane.showMessageDialog(null,"添加借阅卡成功","成功", 1);
                        dispose();
                        new UserManageView(menuView);
                    } else {
                        JOptionPane.showMessageDialog(null,"添加借阅卡失败","请重试", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"请输入ID","请重试", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(jPanel, BorderLayout.SOUTH);
    }

    public void getData() {
        List<User> all = userService.findAll();
        rowData = new Object[all.size()][6];
        for (int i = 0; i < all.size(); i++) {
            User user = all.get(i);
            rowData[i][0] = user.getId();
            rowData[i][1] = user.getUsername();
            rowData[i][2] = user.getPassword();
            rowData[i][3] = user.getCard();
            rowData[i][4] = user.getSex();
            rowData[i][5] = user.getAge();
        }
    }
}
