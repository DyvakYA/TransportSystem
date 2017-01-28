package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;
import model.services.UserServiceable;

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

	public Optional<User> login(String email, String password)  {

		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			Optional <User> user1 = userDao.getUserByEmail(email);
			System.out.println(user1.get().getPasswordHash());
			System.out.println(User.calcPasswordHash(password));

			return userDao.getUserByEmail(email)
					.filter(user -> (user.calcPasswordHash(password))
							.equals(user.getPasswordHash()));
		}
	}

	public List<User> getAll()  {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			return userDao.findAll();
		}
	}

	public Optional<User> getByEmail(String email)  {
		DaoConnection connection = daoFactory.getConnection();
		connection.begin();
		UserDao userDao = daoFactory.createUserDao(connection);
		return userDao.getUserByEmail(email);
	}

	@Override
	public void create(User user) {
		DaoConnection connection = daoFactory.getConnection();
		connection.begin();
		UserDao userDao = daoFactory.createUserDao(connection);
		userDao.create(user);
		connection.commit();
	}

	@Override
	public void update(User user, int id) {
		DaoConnection connection = daoFactory.getConnection();
		connection.begin();
		UserDao userDao = daoFactory.createUserDao(connection);
		userDao.update(user, id);
		connection.commit();
	}


	@Override
	public void delete(int id) {
		try (DaoConnection connection = daoFactory.getConnection()) {
			connection.begin();
			UserDao userDao = daoFactory.createUserDao(connection);
			userDao.delete(id);
			connection.commit();
		}
	}
}
