package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DriverDao;
import model.entities.Driver;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class DriverService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DriverService INSTANCE = new DriverService();
    }

    public static DriverService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Driver> getAllDrivers() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            return driverDao.findAll();
        }
    }

    public void createDriver(Driver driver) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao transportDao = daoFactory.createDriverDao(connection);

            transportDao.create(driver);
        }
    }

    public void updateDriver(Driver driver,int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            driverDao.update(driver, id);
        }
    }

    public void deleteDriver(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DriverDao driverDao = daoFactory.createDriverDao(connection);
            driverDao.delete(id);
        }
    }
}
