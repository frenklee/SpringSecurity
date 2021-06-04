package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

@Component
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;


   /* private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    /*private static final String url = "jdbc:mysql://localhost:3306/db";
    private static final String username = "max";
    private static final String password = "max";

    private static Connection connection;

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/


    @Override
    public List<User> listUsers() {
        List<User> result=null;
        try{
            result = entityManager.createQuery("SELECT e FROM User e",
                    User.class).getResultList();

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;


        /*List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM User");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setWeight(resultSet.getInt("weight"));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
        //return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
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
        /*
        return jdbcTemplate.query("SELECT * FROM user WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);*/
    }

    @Override
    public void updateUser(int id, User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
        /*PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE user SET name=?,age=?,weight=? WHERE id=?");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setInt(3,user.getWeight());
            preparedStatement.setInt(4,id);
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
        /*jdbcTemplate.update("UPDATE user SET name=?,age=?,weight=? WHERE id=?",
                user.getName(),user.getAge(),user.getWeight(),id);
    }*/

    @Override
    public void deleteUser(int id) {
        try {
            entityManager.getTransaction().begin();
            User user = (User)entityManager.find(User.class, id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
        /*PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?");
            preparedStatement.setInt(1,id);
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
        // jdbcTemplate.update("DELETE FROM user WHERE id=?",id);
    }

    @Override
    public void addUser(User user) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();

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
