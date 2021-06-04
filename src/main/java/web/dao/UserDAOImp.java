package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;
import web.model.UserMapper;
import web.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> listUsers() {
        List<User> result=null;
        try{
            result = entityManager.createQuery("SELECT e FROM User e").getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public User getUser(int id) {

        User user = null;

        try{
            user = (User)entityManager.find(User.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(int id, User user) {
        try {
            entityManager.merge(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void deleteUser(int id) {
        try {
            User user = (User)entityManager.find(User.class, id);
            entityManager.remove(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {

        try {
            entityManager.persist(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        /*PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (1,?,?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setInt(3,user.getWeight());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
        //jdbcTemplate.update("INSERT INTO user VALUES (1,?,?,?)",user.getName(),user.getAge(),user.getWeight());
    }
}
