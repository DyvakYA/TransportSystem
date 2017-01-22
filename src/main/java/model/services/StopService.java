package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.StopDao;
import model.entities.Stop;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class StopService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final StopService INSTANCE = new StopService();
    }

    public static StopService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Stop> getAllStops() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            return stopDao.findAll();
        }
    }

    public void createStop(Stop stop) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);

            stopDao.create(stop);
        }
    }

    public void updateStop(Stop route,int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            stopDao.update(route, id);
        }
    }

    public void deleteStop(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            StopDao stopDao = daoFactory.createStopDao(connection);
            stopDao.delete(id);
        }
    }
}
