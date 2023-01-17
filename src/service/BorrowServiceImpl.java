package service;

import dao.BorrowDao;
import dao.BorrowDaoImpl;
import domain.Borrow;

import java.util.List;

public class BorrowServiceImpl implements BorrowService{
    private BorrowDao borrowDao = new BorrowDaoImpl();

    @Override
    public boolean borrowBook(Borrow borrow) {
        return borrowDao.borrowBook(borrow);
    }

    @Override
    public boolean returnBook(Borrow borrow) {
        return borrowDao.returnBook(borrow);
    }

    @Override
    public Borrow findByUserIdAndBookId(int userId, int BookId) {
        return borrowDao.findByUserIdAndBookId(userId, BookId);
    }

    @Override
    public List<Borrow> findAll() {
        return borrowDao.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return borrowDao.deleteById(id);
    }

    @Override
    public boolean updateById(Borrow borrow) {
        return borrowDao.updateById(borrow);
    }
}
