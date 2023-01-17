package service;

import dao.UserDao;
import dao.UserDaoImpl;
import domain.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }

    @Override
    public boolean deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean addCard(User user) {
        return userDao.addCard(user);
    }

    @Override
    public User findByCardAndName(String username, String card) {
        return userDao.findByCardAndName(username, card);
    }
}
