package net.sublime.warehouse.service.user;

import net.sublime.warehouse.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(long id);
    void deleteUser(long id);
    void createUser(User user);
}
