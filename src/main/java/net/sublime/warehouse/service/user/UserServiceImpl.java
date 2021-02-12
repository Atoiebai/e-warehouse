package net.sublime.warehouse.service.user;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.User;
import net.sublime.warehouse.reposirtory.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
}
