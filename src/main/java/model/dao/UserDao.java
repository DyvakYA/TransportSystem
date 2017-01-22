package model.dao;

import model.entities.User;

import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface UserDao extends GenericDao<User>{

    Optional<User> getUserByEmail(String name);

    Optional<User> findByName(String login);

//    User authentication(String login, int passwordHash);
}