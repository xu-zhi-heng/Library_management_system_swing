package service;

import domain.Borrow;

import java.util.List;

public interface BorrowService {
    // 借书操作
    public boolean borrowBook(Borrow borrow);

    // 还书
    public boolean returnBook(Borrow borrow);

    // 根据用户id和图书id查询借书记录
    public Borrow findByUserIdAndBookId(int userId, int BookId);

    public List<Borrow> findAll();

    public boolean deleteById(int id);

    public boolean updateById(Borrow borrow);
}
