package model.dao;

import model.entities.Route;
import model.entities.Stop;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StopDao extends GenericDao<Stop>{
    Optional<Stop> findById(int id) throws SQLException;
    List<Optional<Stop>> findAll() throws SQLException;
    List<Optional<Stop>> findAllStopsOnRoute(Route route) throws SQLException;
}
