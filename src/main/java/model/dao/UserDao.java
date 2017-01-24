package model.dao;

import model.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface UserDao extends GenericDao<User>{

    Optional<User> findById(int id);
    List<Optional<User>> findAll() throws SQLException;
    Optional<User> getUserByEmail(String name) throws SQLException;
    Optional<User> findByName(String login) throws SQLException;

}