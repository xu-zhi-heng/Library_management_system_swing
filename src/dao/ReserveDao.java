package dao;

import domain.Reserve;

import java.util.List;

public interface ReserveDao {
    // 预定书
    public boolean reserveBook(Reserve reserve);

    // 预定完后，书借出，改变状态
    public boolean updateReserve(Reserve reserve);
    public List<Reserve> findAll();

    public boolean deleteById(int id);

    public List<Reserve> findByBookId(int bookId);

}
