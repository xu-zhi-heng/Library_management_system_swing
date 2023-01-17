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
import java.util.List;

public class ReturnBookView extends JDialog {
    private BookService bookService = new BookServiceImpl();
    private BorrowService borrowService = new BorrowServiceImpl();
    private UserService userService = new UserServiceImpl();
    private ReserveService reserveService = new ReserveServiceImpl();

    private JLabel isbnLabel = new JLabel("ISBN: ");
    private JTextField isbnText = new JTextField();
    private JLabel bookNameLabel = new JLabel("书名: ");
    private JTextField bookNameText = new JTextField();
    private JLabel userNameLabel = new JLabel("用户名: ");
    private JTextField userNameText = new JTextField();
    private JLabel idLabel = new JLabel("用户ID: ");
    private JTextField idText = new JTextField();
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    private JButton returnButton = new JButton("确定还书");

    public ReturnBookView(MenuView menuView) {
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

        jPanel.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = idText.getText().trim();
                String userName = userNameText.getText().trim();
                String isbn = isbnText.getText().trim();
                String bookName = bookNameText.getText().trim();
                if (userId.equals("") || userName.equals("") || isbn.equals("") || bookName.equals("")) {
                    JOptionPane.showMessageDialog(null,"请输入完整数据","数据为空", JOptionPane.ERROR_MESSAGE);
                } else {
                    User userById = userService.findUserById(Integer.parseInt(userId));
                    if ((userById != null) && !userById.getUsername().equals(userName)) {
                        JOptionPane.showMessageDialog(null,"用户信息不对","请输入正确的用户", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Book book = bookService.findByCardAndName(bookName, isbn);
                        if (book != null) {
                            int bookId = book.getId();
                            Borrow borrow = borrowService.findByUserIdAndBookId(Integer.parseInt(userId), bookId);
                            if (borrow == null) {
                                JOptionPane.showMessageDialog(null,"没有此条借书记录","错误", JOptionPane.ERROR_MESSAGE);
                            } else {
                                borrow.setIsBorrow(0);
                                // 添加还书日期
                                Date returnDate = new java.sql.Date(new Date().getTime());
                                borrow.setReturnDate(returnDate);
                                boolean b = borrowService.returnBook(borrow);
                                if (b) {
                                    boolean b1 = bookService.setBookStatus(0, bookId);
                                    if (!b1) {
                                        JOptionPane.showMessageDialog(null,"还书失败","请重试", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null,"还书成功","成功", 1);

                                        // 还书成功后，看是否有该图书的预定信息，有，将该书借阅给最近的预定信息
                                        // 先根据日期进行排序，如果日期一样，则通过id选择最小的
                                        List<Reserve> byBookIdList = reserveService.findByBookId(book.getId());
                                        Date latestDate = byBookIdList.get(0).getReserveTime();
                                        // 表示第几条日期就最近的
                                        int index = 0;
                                        if (byBookIdList.size() != 0) {
                                            for (int i = 1; i < byBookIdList.size(); i++) {
                                                Date reserveDate = byBookIdList.get(i).getReserveTime();
                                                // 如果reserveDate比latestDate小
                                                if (reserveDate.compareTo(latestDate) < 0) {
                                                    latestDate = reserveDate;
                                                    index = i;
                                                }
                                            }
                                        }

                                        Reserve reserve = byBookIdList.get(index);
                                        // 添加借阅信息
                                        Borrow newBorrow = new Borrow();
                                        newBorrow.setUserId(reserve.getUserId());
                                        newBorrow.setBookId(reserve.getBookId());
                                        newBorrow.setBorrowDate(new java.sql.Date(new Date().getTime()));
                                        newBorrow.setIsBorrow(1);
                                        boolean b2 = borrowService.borrowBook(newBorrow);
                                        // 更改借阅信息
                                        if (b2) {
                                            reserve.setIsReserve(1);
                                            reserve.setBorrowDate(new java.sql.Date(new Date().getTime()));
                                            reserveService.updateReserve(reserve);
                                            // 重新设置书的状态
                                            bookService.setBookStatus(1, bookId);
                                        } else {
                                            JOptionPane.showMessageDialog(null,"预定借阅失败","请重试", JOptionPane.ERROR_MESSAGE);
                                        }
                                        dispose();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null,"还书失败","请重试", JOptionPane.ERROR_MESSAGE);
                                }
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
