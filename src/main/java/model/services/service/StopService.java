package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.StopDao;
import model.entities.Stop;
import model.services.StopServiceable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class StopService implements StopServiceable{

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final StopService INSTANCE = new StopService();
    }

    public static StopService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Optional<Stop>> getAll() throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            return stopDao.findAll();
        }
    }

    public void create(Stop stop) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            stopDao.create(stop);
            connection.commit();
        }
    }

    public void update(Stop route,int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            stopDao.update(route, id);
        }
    }

    public void delete(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            stopDao.delete(id);
            connection.commit();
        }
    }
}
