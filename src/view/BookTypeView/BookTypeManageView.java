package view.BookTypeView;
import domain.BookType;
import service.BookTypeService;
import service.BookTypeServiceImpl;
import view.MenuView;
import view.UserView.UserManageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookTypeManageView extends JDialog {
    private BookTypeService bookTypeService = new BookTypeServiceImpl();

    public JButton deleteButton = new JButton("删除");
    public JButton updateButton = new JButton("修改图书类别");
    public JButton addButton = new JButton("添加图书类别");

    public JLabel idLabel = new JLabel("书类别ID:");
    public JTextField idText = new JTextField();

    public JPanel jPanel = new JPanel();

    public Object[][] rowData = null;
    public String[] cNames = {"主键", "类别名称"};

    public BookTypeManageView(MenuView menuView) {
        super(menuView, "图书类别管理", true);
        init(menuView);
        setTitle("图书类别管理");
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

        idLabel.setPreferredSize(new Dimension(60, 30));
        idText.setPreferredSize(new Dimension(100, 30));
        jPanel.add(idLabel);
        jPanel.add(idText);
        jPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idText.getText().equals("")) {
                    int id = Integer.parseInt(idText.getText());
                    boolean b = bookTypeService.deleteBookTypeById(id);
                    if (b) {
                        JOptionPane.showMessageDialog(null,"删除类别成功","成功", 1);
                        dispose();
                        new BookTypeManageView(menuView);
                    } else {
                        JOptionPane.showMessageDialog(null,"删除类别失败","请重试", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"请输入ID","请重试", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jPanel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!idText.getText().equals("")) {
                    int id = Integer.parseInt(idText.getText());
                    BookType bookType = bookTypeService.findById(id);
                    String typeName = JOptionPane.showInputDialog(null,"请输入更新名称","输入数据", 1);
                    if (!typeName.equals("")) {
                        bookType.setTypeName(typeName);
                        boolean flag = bookTypeService.updateBookType(bookType);
                        if (flag) {
                            JOptionPane.showMessageDialog(null,"更新类别成功","成功", 1);
                            dispose();
                            new BookTypeManageView(menuView);
                        } else {
                            JOptionPane.showMessageDialog(null,"更新类别失败","失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"请输入类别名称","提醒", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"请输入ID","请重试", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String typeName = JOptionPane.showInputDialog(null,"请输入类别名称","输入数据", 1);
                if (!typeName.equals("")) {
                    // 判断该类别是否存在
                    BookType byName = bookTypeService.findByName(typeName);
                    if (byName == null) {
                        BookType bookType = new BookType();
                        bookType.setTypeName(typeName);
                        boolean flag = bookTypeService.addBookType(bookType);
                        if (flag) {
                            JOptionPane.showMessageDialog(null,"添加类别成功","成功", 1);
                            dispose();
                            new BookTypeManageView(menuView);
                        } else {
                            JOptionPane.showMessageDialog(null,"添加类别失败","失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(null,"该类别已经存在","提醒", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"请输入类别名称","提醒", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(jPanel, BorderLayout.SOUTH);
    }

    public void getData() {
        List<BookType> all = bookTypeService.findAll();
        rowData = new Object[all.size()][2];
        for (int i = 0; i < all.size(); i++) {
            BookType bookType = all.get(i);
            rowData[i][0] = bookType.getId();
            rowData[i][1] = bookType.getTypeName();
        }
    }
}
