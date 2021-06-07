package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

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
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        user1.setWeight(user.getWeight());
        userDAO.addUser(user);
    }
}
