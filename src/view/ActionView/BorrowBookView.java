package view.ActionView;
import domain.Book;
import domain.Borrow;
import domain.Reserve;
import domain.User;
import service.*;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BorrowBookView extends JDialog {
    private BookService bookService = new BookServiceImpl();
    private BorrowService borrowService = new BorrowServiceImpl();
    private UserService userService = new UserServiceImpl();
    private ReserveService reserveService = new ReserveServiceImpl();

    private JLabel isbnLabel = new JLabel("isbn: ");
    private JTextField isbnText = new JTextField();
    private JLabel bookNameLabel = new JLabel("书名: ");
    private JTextField bookNameText = new JTextField();
    private JLabel userNameLabel = new JLabel("用户名: ");
    private JTextField userNameText = new JTextField();
    private JLabel idLabel = new JLabel("用户ID: ");
    private JTextField idText = new JTextField();
    private JLabel cardLabel = new JLabel("借阅卡号: ");
    private JTextField cardText = new JTextField();
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    private JButton borrowButton = new JButton("确定借书");

    public BorrowBookView(MenuView menuView) {
        super(menuView, "借书", true);
        isbnLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(isbnLabel);
        isbnText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(isbnText);

        bookNameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(bookNameLabel);

        bookNameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(bookNameText);

        idLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(idLabel);

        idText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(idText);

        userNameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(userNameLabel);

        userNameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(userNameText);

        cardLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(cardLabel);

        cardText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(cardText);

        jPanel.add(borrowButton);

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = idText.getText().trim();
                String userName = userNameText.getText().trim();
                String isbn = isbnText.getText().trim();
                String bookName = bookNameText.getText().trim();
                String card = cardText.getText().trim();
                if (userId.equals("") || userName.equals("") || isbn.equals("") || bookName.equals("") || card.equals("")) {
                    JOptionPane.showMessageDialog(null,"请输入完整数据","数据为空", JOptionPane.ERROR_MESSAGE);
                } else {
                    User userById = userService.findUserById(Integer.parseInt(userId));
                    if ((userById != null) && (userById.getCard() == null || userById.getCard().equals(""))) {
                        JOptionPane.showMessageDialog(null,"该用户没有借阅卡号","请添加借阅卡号", JOptionPane.ERROR_MESSAGE);
                    } else if (!userById.getCard().equals(card)){
                        JOptionPane.showMessageDialog(null,"该用户的借阅卡号不对","请输入正确的借阅卡号", JOptionPane.WARNING_MESSAGE);
                    } else if (!userById.getUsername().equals(userName)) {
                        JOptionPane.showMessageDialog(null,"用户姓名不对","请输入正确的用户姓名", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Book byCardAndName = bookService.findByCardAndName(bookName, isbn);
                        if (byCardAndName == null) {
                            JOptionPane.showMessageDialog(null,"请确认该图书的信息是否正确","没有查到该图书", JOptionPane.WARNING_MESSAGE);
                        } else if (byCardAndName.getIsBorrow() == 1){
                            int isReserve = JOptionPane.showConfirmDialog(null, "是否预定?", "该图书现在正在借阅中", JOptionPane.YES_NO_CANCEL_OPTION);
                            if (isReserve == 0) {
                                Reserve reserve = new Reserve();
                                reserve.setBookId(byCardAndName.getId());
                                reserve.setUserId(Integer.parseInt(userId));
                                reserve.setIsReserve(0);
                                Date reserveTime = new java.sql.Date(new Date().getTime());
                                reserve.setReserveTime(reserveTime);
                                boolean b = reserveService.reserveBook(reserve);
                                if (b) {
                                    JOptionPane.showMessageDialog(null,"预定图书成功","成功", 1);
                                } else {
                                    JOptionPane.showMessageDialog(null,"预定图书失败","请重试", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            Borrow borrow = new Borrow();
                            borrow.setBookId(byCardAndName.getId());
                            borrow.setUserId(Integer.parseInt(userId));
                            borrow.setIsBorrow(1);
                            Date borrowDate = new java.sql.Date(new Date().getTime());
                            borrow.setBorrowDate(borrowDate);
                            boolean b = borrowService.borrowBook(borrow);
                            if (b) {
                                boolean b1 = bookService.setBookStatus(1, byCardAndName.getId());
                                if (!b1) {
                                    JOptionPane.showMessageDialog(null,"借阅图书失败","请重试", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null,"借阅图书成功","成功", 1);
                                    dispose();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,"借阅图书失败","请重试", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        add(jPanel);
        setTitle("借书");
        setSize(350,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
