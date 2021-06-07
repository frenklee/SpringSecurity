package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class jdbcUserDAO implements UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/db";
    private static final String USERNAME = "max";
    private static final String PASSWORD = "max";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<User> listUsers() {
        List<User> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User person = new User();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setWeight(resultSet.getInt("weight"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    @Override
    public User getUser(int id) {
        User person = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM User WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new User();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setWeight(resultSet.getInt("weight"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;
    }

    @Override
    public void updateUser(int id, User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE User SET name=?, age=?, weight=? WHERE id=?");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getWeight());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM User WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO User VALUES(1, ?, ?, ?)");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setInt(3, user.getWeight());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
