package view.BookView;

import domain.Book;
import service.BookService;
import service.BookServiceImpl;
import service.BookTypeService;
import service.BookTypeServiceImpl;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookManageView extends JDialog {
    private BookService bookService = new BookServiceImpl();
    private BookTypeService bookTypeService = new BookTypeServiceImpl();

    public JButton deleteButton = new JButton("删除");
    public JButton updateButton = new JButton("更新");
    public JButton addButton = new JButton("添加图书");

    public JLabel nameLabel = new JLabel("书ID:");
    public JTextField nameText = new JTextField();

    public JPanel jPanel = new JPanel();

    public Object[][] rowData = null;
    public String[] cNames = {"主键", "isbn", "图书名", "作者", "是否借阅", "图书类别"};

    public BookManageView(MenuView menuView) {
        super(menuView, "图书管理", true);
        init(menuView);
        setTitle("图书管理");
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

        nameLabel.setPreferredSize(new Dimension(30, 30));
        nameText.setPreferredSize(new Dimension(100, 30));
        jPanel.add(nameLabel);
        jPanel.add(nameText);
        jPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(nameText.getText());
                boolean b = bookService.deleteBookById(id);
                if (b) {
                    JOptionPane.showMessageDialog(null,"删除成功","成功", 1);
                    dispose();
                    new BookManageView(menuView);
                } else {
                    JOptionPane.showMessageDialog(null,"删除图书失败","请重试", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jPanel.add(updateButton);
        Object object = this;
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(nameText.getText());
                Book book = bookService.findById(id);
                new UpdateBookView((BookManageView) object, book, menuView);
            }
        });

        jPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookView((BookManageView) object, menuView);
            }
        });

        add(jPanel, BorderLayout.SOUTH);
    }

    public void getData() {
        List<Book> all = bookService.findAll();
        rowData = new Object[all.size()][6];
        for (int i = 0; i < all.size(); i++) {
            Book book = all.get(i);
            rowData[i][0] = book.getId();
            rowData[i][1] = book.getIsbn();
            rowData[i][2] = book.getBookName();
            rowData[i][3] = book.getAuthor();
            rowData[i][4] = book.getIsBorrow() == 0 ? "未借阅" : "已借阅";
            rowData[i][5] = bookTypeService.findById(book.getBookType()).getTypeName();
        }
    }
}
