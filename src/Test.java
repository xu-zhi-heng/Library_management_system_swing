import domain.Reserve;
import service.BookService;
import service.BookServiceImpl;
import service.ReserveService;
import service.ReserveServiceImpl;
import view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParseException {
//        String card = JOptionPane.showInputDialog(null,"请输入借阅者卡号","输入数据", 1);
//        System.out.println(card);

//        BookService bookService = new BookServiceImpl();
//        System.out.println(bookService.findByCardAndName("白夜行", "464545"));
//        Date date = new Date();

//        int isDelete = JOptionPane.showConfirmDialog(null, "需要预定嘛", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
//        System.out.println(isDelete);
//        ReserveService reserveService = new ReserveServiceImpl();
//        List<Reserve> byBookIdList = reserveService.findByBookId(3);
//        System.out.println(byBookIdList);
//
//        Date latestDate = byBookIdList.get(0).getReserveTime();
//        int index = 0;
//        for (int i = 1; i < byBookIdList.size(); i++) {
//            Date reserveDate = byBookIdList.get(i).getReserveTime();
//            // 如果reserveDate比latestDate小
//            if (reserveDate.compareTo(latestDate) < 0) {
//                latestDate = reserveDate;
//                index = i;
//            }
//        }
//        System.out.println(index);
//        System.out.println(latestDate);

        JFrame jf=new JFrame("背景图片测试");
        URL imgUrl = Test.class.getClassLoader().getResource("./img/main.jpg");
        System.out.println(imgUrl);
        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg = new ImageIcon(imgUrl);
        JLabel label=new JLabel(bg);
        label.setBounds(0, 0, bg.getIconWidth(),bg.getIconHeight());
        jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)jf.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        //3.之后把组件和面板添加到窗口面板就可以；
        JButton btn=new JButton("测试按钮");
        pan.add(btn);

        jf.setSize(bg.getIconWidth(),bg.getIconHeight());
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
