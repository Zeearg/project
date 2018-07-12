package net.project.service;

import net.project.dao.UserDao;
import net.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public UserServiceImpl(){}

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findByID(int id) {
        return userDao.findByID(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public List<User> searchUsers(String search) {
        return userDao.searchUsers(search);
    }
}
