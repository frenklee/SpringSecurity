package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        userDAO.updateUser(id,user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setName(user.getUsername());
        user1.setAge(user.getAge());
        user1.setWeight(user.getWeight());
        userDAO.addUser(user);
    }

    @Override
    public User getUserByLogin(String name) {
        return userDAO.getUserByLogin(name);
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDAO.getUserByLogin(name);
    }
}
