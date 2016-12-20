package web.service;

import web.entity.User;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface UserService {

    User getUser(String login);

}