package service;

import domain.Reserve;

import java.util.List;

public interface ReserveService {
    // 预定书
    public boolean reserveBook(Reserve reserve);

    // 预定完后，书借出，改变状态
    public boolean updateReserve(Reserve reserve);

    public List<Reserve> findAll();

    public boolean deleteById(int id);

    // 根据书的id查看所有预定信息
    public List<Reserve> findByBookId(int bookId);
}
