package model.dao.jdbc;

import model.dao.UserDao;
import model.entities.User;
import model.entities.enums.UserRoles;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcUserDao implements UserDao {

    private static final String SELECT_FROM_USERS_WHERE_USER_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String SELECT_FROM_USERS = "SELECT * FROM users";

    private static final String SELECT_USER_BY_EMAIL ="SELECT * FROM users WHERE lower(email) = ?";

    //private static final String SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD_HASH = "SELECT * FROM users WHERE login=?  AND  password_hash=?";
    private static final String SELECT_FROM_USERS_WHERE_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SELECT_FROM_USERS_WHERE_NAME = "SELECT * FROM users WHERE name=?";

    private static final String CREATE_USER_QUERY = "INSERT INTO users (login , name, surname, password_hash, role)  VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET login=?, name=?, surname=?, password_hash=?, role=? WHERE user_id=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcUserDao() {}

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS_WHERE_USER_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user = Optional.of(getUserFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }

    @Override
    public List<Optional<User>> findAll() throws SQLException {
        List<Optional<User>> users = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            users.add(Optional.of(getUserFromResultSet(result)));
        }
        return users;
    }

    @Override
    public void create(User user) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS );
        query.setString( 1 , user.getName());
        query.setString( 2 , user.getSurname());
        query.setString( 3 , user.getEmail());
        query.setInt(4, user.getPasswordHash());
        query.setString( 5 , String.valueOf(user.getRole()));
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            user.setId( keys.getInt(1) );
        }
    }

    @Override
    public void update(User user, int id) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(UPDATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setString( 1 , user.getName());
        query.setString( 2 , user.getSurname());
        query.setString( 3 , user.getEmail());
        query.setInt(4, user.getPasswordHash());
        query.setString( 5 , String.valueOf(user.getRole()));
        query.setInt(6, id);
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            user.setId( keys.getInt(1) );
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public Optional<User> findByName(String name) throws SQLException {
        Optional<User> user = Optional.empty();
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_NAME);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            user = Optional.of(getUserFromResultSet(result));
        }
        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws SQLException {
        Optional<User> user = Optional.empty();
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
        statement.setString(1, email.toLowerCase());
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            user = Optional.of(getUserFromResultSet(result));
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User.Builder()
                .setId(rs.getInt("user_id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setEmail(rs.getString("email"))
                .setPasswordHash(rs.getString("password"))
                .setRole(UserRoles.valueOf(rs.getString("role")))
                .build();
        return user;
    }


//    @Override
//    public User authentication(String login, int passwordHash) {
//
//        User past_user = null;
//
//        try (PreparedStatement statement = connection
//                .prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD_HASH)) {
//
//            statement.setString(1, login);
//            statement.setInt(2, passwordHash);
//
//            ResultSet result = statement.executeQuery();
//
//            if (result.next()) {
//                past_user = new User(result.getInt(1),
//                        result.getString(2),
//                        result.getString(3),
//                        result.getString(4),
//                        result.getInt(5),
//                        UserRoles.valueOf(result.getString(6).toUpperCase()));
//                System.out.println(past_user);
//            }
//
//        } catch (SQLException e) {
//            Logger logger = LoggerMaker.getInstance().getLogger();
//            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
//        }
//        return past_user;
//    }
}
