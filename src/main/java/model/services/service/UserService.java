package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.services.UserServiceable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserService implements UserServiceable{

	private DaoFactory daoFactory = DaoFactory.getInstance();

	private static class Holder {
		static final UserService INSTANCE = new UserService();
	}

	public static UserService getInstance() {
		return Holder.INSTANCE;
	}

	public Optional<User> login(String name, String password) throws SQLException {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			return userDao.getUserByEmail(name)
					.filter(user -> (user.calcPasswordHash(password))
							.equals(user.getPasswordHash()));
		}
	}

	public List<Optional<User>> getAll() throws SQLException {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			return userDao.findAll();
		}
	}

	@Override
	public void create(User user) {

	}

	@Override
	public void update(User user, int id) {

	}

	@Override
	public void delete(int id) {

	}
}
