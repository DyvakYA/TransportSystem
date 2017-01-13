package dao.jdbc;

import dao.UserDao;
import entities.enums.UserRoles;
import entities.User;
import extras.Localization;
import extras.LoggerMaker;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcUserDao implements UserDao {

    private static final String SELECT_FROM_USERS_WHERE_USER_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String SELECT_FROM_USERS = "SELECT * FROM users";

    private static final String SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD_HASH = "SELECT * FROM users WHERE login=?  AND  password_hash=?";
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
    public User findById(int id) {

        User user = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS_WHERE_USER_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        UserRoles.valueOf(result.getString(6).toUpperCase()));
            }

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                users.add(new User(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        UserRoles.valueOf(result.getString(6).toUpperCase())));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return users;
    }

    @Override
    public void create(User user) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_USER_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , user.getLogin());
            query.setString( 2 , user.getName());
            query.setString( 3 , user.getSurname());
            query.setInt(4, user.getPasswordHash());
            query.setString( 5 , String.valueOf(user.getRole()));
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                user.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(User user, int id) {

        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_USER_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , user.getLogin());
            query.setString( 2 , user.getName());
            query.setString( 3 , user.getSurname());
            query.setInt(4, user.getPasswordHash());
            query.setString( 5 , String.valueOf(user.getRole()));
            query.setInt(6, id);

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                user.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {

        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_USER_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN)) {

            statement.setString(1, login);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        UserRoles.valueOf(result.getString(6).toUpperCase()));
            }

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }

    @Override
    public List<User> findByName(String name) {

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS_WHERE_NAME)) {

            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                users.add(new User(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        UserRoles.valueOf(result.getString(6).toUpperCase())));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return users;
    }

    @Override
    public User authentication(String login, int passwordHash) {

        User user = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD_HASH)) {

            statement.setString(1, login);
            statement.setInt(2, passwordHash);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getInt(5),
                        UserRoles.valueOf(result.getString(6).toUpperCase()));
                System.out.println(user);
            }

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return user;
    }
}
