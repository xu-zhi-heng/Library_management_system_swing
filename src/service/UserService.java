package service;

import domain.User;

import java.util.List;

public interface UserService {
    // 登录
    public User login(String username, String password);
    // 注册
    public boolean register(User user);
    // 根据用户id删除该用户
    public boolean deleteUserById(int id);
    // 添加阅读者
    public boolean addUser(User user);
    // 更新阅读者
    public boolean updateUserById(User user);
    // 根据id查询用户信息
    public User findUserById(int id);
    // 查询所有用户
    public List<User> findAll();
    // 添加借阅卡号
    public boolean addCard(User user);
    // 根据用户姓名和卡号查询对应的用户
    public User findByCardAndName(String username, String card);
}
