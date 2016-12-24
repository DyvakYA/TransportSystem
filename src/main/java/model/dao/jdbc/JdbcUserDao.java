package model.dao.jdbc;

import model.dao.UserDao;
import model.entities.users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcUserDao implements UserDao {

    private static final String SELECT_FROM_USERS = "SELECT * FROM users";
    private static final String USER_NAME = "user_name";
    private static final String USER_ID = "user_id";
    private static final String INSERT_INTO_USER = "INSERT INTO user (user_name) VALUES ( ? )";
    private Connection connection;

    public JdbcUserDao() {}

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_FROM_USERS)){

            while (resultSet.next()) {
                result.add((User) new User.Builder()

                        .setId(resultSet.getInt(USER_ID))
                        .setName(resultSet.getString(USER_NAME), resultSet.wasNull())
                        .build());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void create(User user) {

        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_USER
                             , Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, user.getName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public User autentify(String login, int passwordHash) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
