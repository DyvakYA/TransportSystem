package model.dao;

import model.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface UserDao extends GenericDao<User>{

    Optional<User> findById(int id);
    List<User> findAll() ;
    Optional<User> getUserByEmail(String name) ;
    Optional<User> findByName(String login) ;
}