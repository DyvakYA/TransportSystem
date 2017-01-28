package model.dao;

import model.dao.exception.DAOException;
import model.entities.Route;
import model.entities.RouteStops;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RouteStopsDao extends GenericDao<RouteStops>{
    Optional<RouteStops> findById(int id) throws SQLException, DAOException;
    List<RouteStops> findAll() throws SQLException, DAOException;
    List<Optional<RouteStops>> findByRoute(Route route) throws SQLException, DAOException;

}
