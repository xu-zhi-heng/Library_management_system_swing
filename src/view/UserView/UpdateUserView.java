package view.UserView;
import domain.User;
import service.*;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserView extends JDialog {

    private UserService userService = new UserServiceImpl();

    public JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    public JLabel nameLabel = new JLabel("用户名:", JLabel.LEFT);
    public JTextField nameText = new JTextField();
    public JLabel passLabel = new JLabel("密码:", JLabel.LEFT);
    public JTextField passText = new JTextField();
    public JLabel cardLabel = new JLabel("借阅卡号", JLabel.LEFT);
    public JTextField cardText = new JTextField();
    public JLabel sexLabel = new JLabel("性别", JLabel.LEFT);
    public JTextField sexText = new JTextField();
    public JLabel ageLabel = new JLabel("年龄", JLabel.LEFT);
    public JTextField ageText = new JTextField();

    private JButton add = new JButton("提交");

    public UpdateUserView(UserManageView userManageView, User user, MenuView menuView) {

        super(userManageView, "更新用户", true);

        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);
        nameText.setPreferredSize(new Dimension(200, 30));
        nameText.setText(user.getUsername());
        jPanel.add(nameText);

        passLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(passLabel);

        passText.setPreferredSize(new Dimension(200, 30));
        passText.setText(user.getPassword());
        jPanel.add(passText);

        cardLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(cardLabel);

        cardText.setPreferredSize(new Dimension(200, 30));
        cardText.setText(user.getCard());
        jPanel.add(cardText);

        sexLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(sexLabel);

        sexText.setPreferredSize(new Dimension(200, 30));
        sexText.setText(user.getSex());
        jPanel.add(sexText);

        ageLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(ageLabel);

        ageText.setPreferredSize(new Dimension(200, 30));
        ageText.setText(user.getAge() + "");
        jPanel.add(ageText);

        jPanel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User newUser = new User();
                if (nameText.getText().equals("") || passText.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,"用户名或者密码不能为空","请重试", JOptionPane.ERROR_MESSAGE);
                } else {
                    newUser.setId(user.getId());
                    newUser.setUsername(nameText.getText());
                    newUser.setPassword(passText.getText());
                    newUser.setCard(cardText.getText());
                    newUser.setSex(sexText.getText());
                    newUser.setAge(Integer.parseInt(ageText.getText()));
                    boolean flag = userService.updateUserById(newUser);
                    if (flag) {
                        JOptionPane.showMessageDialog(null,"更新用户成功","成功", 1);
                        dispose();
                        userManageView.dispose();
                        new UserManageView(menuView);
                    } else {
                        JOptionPane.showMessageDialog(null,"更新用户失败","失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        Container container = getContentPane();
        container.add(jPanel);
        setSize(350,400);
        setTitle("更新用户");
        setLocationRelativeTo(null);
        // 只销毁当前的窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
