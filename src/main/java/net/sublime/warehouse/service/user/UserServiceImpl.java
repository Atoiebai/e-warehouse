package net.sublime.warehouse.service.user;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.user.Role;
import net.sublime.warehouse.model.user.User;
import net.sublime.warehouse.reposirtory.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        user.setActive(true);
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
