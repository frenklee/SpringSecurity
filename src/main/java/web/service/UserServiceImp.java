package web.service;


import org.springframework.stereotype.Component;
import web.dao.UserDAOImp;
import web.model.User;

import java.util.List;

@Component
public class UserServiceImp implements UserService{

    UserDAOImp userDAO;

    @Override
    public List<User> listUsers() {
        return userDAO.listUsers();
    }

    @Override
    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    @Override
    public void updateUser(int id, User user) {
        userDAO.updateUser(id,user);
    }

    @Override
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {
     userDAO.addUser(user);
    }
}
