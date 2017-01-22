package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.RouteDao;
import model.entities.Route;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class RouteService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final RouteService INSTANCE = new RouteService();
    }

    public static RouteService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Route> getAllRoutes() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            return routeDao.findAll();
        }
    }

    public void createRoute(Route route) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            routeDao.create(route);
        }
    }

    public void updateRoute(Route route,int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            routeDao.update(route, id);
        }
    }

    public void deleteRoute(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            RouteDao routeDao = daoFactory.createRouteDao(connection);
            routeDao.delete(id);
        }
    }
}
