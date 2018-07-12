package net.project.service;

import net.project.model.User;

import java.util.List;

public interface UserService {

    User findByID(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
    List<User> listUsers();
    List<User> searchUsers(String search);
}
