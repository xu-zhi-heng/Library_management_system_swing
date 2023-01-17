package view.UserView;
import domain.User;
import service.UserService;
import service.UserServiceImpl;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 添加用户界面
public class AddUserView extends JDialog {

    private UserService userService = new UserServiceImpl();

    public JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    public JLabel usernameLabel = new JLabel("用户名:", JLabel.LEFT);
    public JTextField usernameText = new JTextField();
    public JLabel passwordLabel = new JLabel("密码:", JLabel.LEFT);
    public JPasswordField passwordText = new JPasswordField();
    public JLabel cardLabel = new JLabel("借阅卡号", JLabel.LEFT);
    public JTextField cardText = new JTextField();
    public JLabel ageLabel = new JLabel("年龄", JLabel.LEFT);
    public JTextField ageText = new JTextField();
    public JLabel sexLabel = new JLabel("性别", JLabel.LEFT);
    public JComboBox<String> select = new JComboBox<>();
    private JButton add = new JButton("提交");

    public AddUserView(UserManageView userManageView, MenuView menuView) {

        super(userManageView, "添加用户", true);

        usernameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(usernameLabel);
        usernameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(usernameText);

        passwordLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(passwordLabel);

        passwordText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(passwordText);

        cardLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(cardLabel);

        cardText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(cardText);

        ageLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(ageLabel);

        ageText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(ageText);

        sexLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(sexLabel);

        select.addItem("男");
        select.addItem("女");
        select.setPreferredSize(new Dimension(200, 30));
        jPanel.add(select);

        jPanel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                if (usernameText.getText().equals("") || passwordText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"用户名或者密码不能为空","请重试", JOptionPane.ERROR_MESSAGE);
                } else {
                    user.setUsername(usernameText.getText());
                    user.setPassword(passwordText.getText());
                    user.setCard(cardText.getText());
                    user.setSex(select.getSelectedItem().toString());
                    user.setAge(Integer.parseInt(ageText.getText()));
                    boolean flag = userService.addUser(user);
                    if (flag) {
                        JOptionPane.showMessageDialog(null,"添加借阅者成功","成功", 1);
                        dispose();
                        userManageView.dispose();
                        new UserManageView(menuView);
                    } else {
                        JOptionPane.showMessageDialog(null,"添加借阅者失败","失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        Container container = getContentPane();
        container.add(jPanel);

        setSize(350,400);
        setLocationRelativeTo(null);
        // 只销毁当前的窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
