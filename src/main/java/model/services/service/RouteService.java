package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.RouteDao;
import model.entities.Route;
import model.services.RouteServiceable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class RouteService implements RouteServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();



    private static class Holder {
        static final RouteService INSTANCE = new RouteService();
    }

    public static RouteService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Route> getAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            return routeDao.findAll();
        }
    }

    public void create(Route route) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            routeDao.create(route);
            connection.commit();
        }
    }

    public void update(Route route,int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            routeDao.update(route, id);
            connection.commit();
        }
    }

    public void delete(int id)  {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            routeDao.delete(id);
            connection.commit();
        }
    }

    public Optional<Route> findById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            return routeDao.findById(id);
        }
    }
}
