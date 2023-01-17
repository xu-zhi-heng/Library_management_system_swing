package view.BookView;
import domain.Book;
import domain.BookType;
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

// 添加图书界面
public class AddBookView extends JDialog {

    private BookTypeService bookTypeService = new BookTypeServiceImpl();
    private BookService bookService = new BookServiceImpl();

    public JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    public JLabel isbnLabel = new JLabel("isbn:", JLabel.LEFT);
    public JTextField isbnText = new JTextField();
    public JLabel bookNameLabel = new JLabel("书名:", JLabel.LEFT);
    public JTextField bookNameText = new JTextField();
    public JLabel authorLabel = new JLabel("作者", JLabel.LEFT);
    public JTextField authorText = new JTextField();
    public JLabel bookType = new JLabel("图书类别", JLabel.LEFT);
    public JComboBox<String> select = new JComboBox<>();

    private JButton add = new JButton("提交");

    public AddBookView(BookManageView bookManageView, MenuView menuView) {
        super(bookManageView, "添加图书", true);
        isbnLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(isbnLabel);
        isbnText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(isbnText);

        bookNameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(bookNameLabel);

        bookNameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(bookNameText);

        authorLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(authorLabel);

        authorText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(authorText);

        bookType.setPreferredSize(new Dimension(80, 30));
        jPanel.add(bookType);


        List<BookType> all = bookTypeService.findAll();
        for (int i = 0; i < all.size(); i++) {
            select.addItem(all.get(i).getTypeName());
        }
        select.setPreferredSize(new Dimension(200, 30));
        jPanel.add(select);

        jPanel.add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                book.setIsbn(isbnText.getText());
                book.setBookName(bookNameText.getText());
                book.setAuthor(authorText.getText());
                String bookTypeName = select.getSelectedItem().toString();
                for (int i = 0; i < all.size(); i++) {
                    if (all.get(i).getTypeName() == bookTypeName) {
                        book.setBookType(all.get(i).getId());
                    }
                }
                boolean flag = bookService.addBook(book);
                if (flag) {
                    JOptionPane.showMessageDialog(null,"添加图书成功","成功", 1);
                    dispose();
                    bookManageView.dispose();
                    new BookManageView(menuView);
                } else {
                    JOptionPane.showMessageDialog(null,"添加图书失败","失败", JOptionPane.ERROR_MESSAGE);
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
