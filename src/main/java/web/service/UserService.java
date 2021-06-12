package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> listUsers();
    public User getUser(int id);
    public void updateUser(int id, User user);
    public void deleteUser(int id);
    public void addUser(User user);
    public User getUserByLogin(String name);
}
