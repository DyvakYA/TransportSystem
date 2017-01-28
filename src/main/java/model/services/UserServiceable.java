package model.services;

import model.entities.User;

import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface UserServiceable extends GenericService<User> {
    Optional<User> login(String name, String password) ;
}
