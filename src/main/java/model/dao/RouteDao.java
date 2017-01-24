package model.dao;

import model.entities.Route;
import model.entities.Stop;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RouteDao extends GenericDao<Route>{
    Optional<Route> findById(int id) throws SQLException;
    List<Optional<Route>> findAll() throws SQLException;
    List<Optional<Route>> findRoutesByStops(Stop startStop, Stop finishStop) throws SQLException;
}
