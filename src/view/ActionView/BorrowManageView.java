package view.ActionView;
import domain.Borrow;
import service.*;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BorrowManageView extends JDialog {
    private BookService bookService = new BookServiceImpl();
    private BorrowService borrowService = new BorrowServiceImpl();
    private UserService userService = new UserServiceImpl();

    public JButton deleteButton = new JButton("删除记录");
    public JLabel nameLabel = new JLabel("借书记录ID:");
    public JTextField nameText = new JTextField();

    public JPanel jPanel = new JPanel();

    public Object[][] rowData = null;
    public String[] cNames = {"主键", "用户", "图书名", "借书日期", "是否在借阅中", "还书日期"};

    public BorrowManageView(MenuView menuView) {
        super(menuView, "借书记录", true);
        init(menuView);
        setTitle("借书记录");
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

        nameLabel.setPreferredSize(new Dimension(70, 30));
        nameText.setPreferredSize(new Dimension(100, 30));
        jPanel.add(nameLabel);
        jPanel.add(nameText);
        jPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameText.getText().equals("")) {
                    int id = Integer.parseInt(nameText.getText());
                    boolean b = borrowService.deleteById(id);
                    if (b) {
                        JOptionPane.showMessageDialog(null,"删除记录","成功", 1);
                        dispose();
                        new BorrowManageView(menuView);
                    } else {
                        JOptionPane.showMessageDialog(null,"删除记录失败","请重试", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"请输入ID","错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(jPanel, BorderLayout.SOUTH);
    }


    public void getData() {
        List<Borrow> all = borrowService.findAll();
        rowData = new Object[all.size()][6];
        for (int i = 0; i < all.size(); i++) {
            Borrow borrow = all.get(i);
            rowData[i][0] = borrow.getId();
            rowData[i][1] = userService.findUserById(borrow.getUserId()).getUsername();
            rowData[i][2] = bookService.findById(borrow.getBookId()).getBookName();
            rowData[i][3] = borrow.getBorrowDate();
            rowData[i][4] = borrow.getIsBorrow() == 0 ? "已还书" : "借阅中";
            rowData[i][5] = borrow.getReturnDate();
        }
    }
}
