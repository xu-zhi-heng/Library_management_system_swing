package controller;
import view.BookView.BookManageView;
import view.MenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookListen implements ActionListener {
    private MenuView menuView;

    public BookListen(MenuView menuView) {
        this.menuView = menuView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem jMenuItem = (JMenuItem) e.getSource();
        String option = jMenuItem.getText();
        switch (option) {
            case "添加图书":
                break;
            case "所有图书":
                break;
            default:
                System.out.println("没有该操作");
        }

    }
}
