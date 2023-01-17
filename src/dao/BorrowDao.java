package dao;

import domain.Borrow;

import java.util.List;

public interface BorrowDao {
    // 借书操作
    public boolean borrowBook(Borrow borrow);

    // 还书
    public boolean returnBook(Borrow borrow);

    public Borrow findByUserIdAndBookId(int userId, int BookId);

    public List<Borrow> findAll();

    public boolean deleteById(int id);

    public boolean updateById(Borrow borrow);
}
