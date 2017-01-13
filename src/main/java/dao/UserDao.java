package dao;

import entities.User;

import java.util.List;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface UserDao extends GenericDao<User>{

    List<User> findByName (String name);

    User findByLogin(String login);

    User authentication(String login, int passwordHash);
}