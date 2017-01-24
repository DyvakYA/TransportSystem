package model.services;

import model.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface UserServiceable extends GenericService<User> {
    List<Optional<User>> getAll() throws SQLException;
    Optional<User> login(String name, String password) throws SQLException;
}
