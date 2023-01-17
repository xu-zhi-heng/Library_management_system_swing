package service;

import dao.ReserveDao;
import dao.ReserveDaoImpl;
import domain.Reserve;

import java.util.List;

public class ReserveServiceImpl implements ReserveService{
    private ReserveDao reserveDao = new ReserveDaoImpl();

    @Override
    public boolean reserveBook(Reserve reserve) {
        return reserveDao.reserveBook(reserve);
    }

    @Override
    public boolean updateReserve(Reserve reserve) {
        return reserveDao.updateReserve(reserve);
    }

    @Override
    public List<Reserve> findAll() {
        return reserveDao.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return reserveDao.deleteById(id);
    }

    @Override
    public List<Reserve> findByBookId(int bookId) {
        return reserveDao.findByBookId(bookId);
    }
}
