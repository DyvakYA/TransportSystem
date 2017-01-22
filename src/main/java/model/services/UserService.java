package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

import java.util.List;
import java.util.Optional;


public class UserService {

	private DaoFactory daoFactory = DaoFactory.getInstance();

	private static class Holder {
		static final UserService INSTANCE = new UserService();
	}

	public static UserService getInstance() {
		return Holder.INSTANCE;
	}

	public Optional<User> login(String name, String password) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			return userDao.getUserByEmail(name)
					.filter(user -> (user.calcPasswordHash(password))
							.equals(user.getPasswordHash()));
		}
	}

	public List<User> getAllUsers() {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			//DaoFactory.getInstance()
			return userDao.findAll();
		}
	}
}
