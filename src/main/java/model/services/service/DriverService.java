package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DriverDao;
import model.entities.Driver;
import model.services.DriverServiceable;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class DriverService implements DriverServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DriverService INSTANCE = new DriverService();
    }

    public static DriverService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Driver> getAll()  {
        try (DaoConnection connection = daoFactory.getConnection()) {
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            return driverDao.findAll();
        }
    }

    public void create(Driver driver)  {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            driverDao.create(driver);
            connection.commit();
        }
    }

    public void update(Driver driver,int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            driverDao.update(driver, id);
            connection.commit();
        }
    }

    public void delete(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            driverDao.delete(id);
            connection.commit();
        }
    }
}
