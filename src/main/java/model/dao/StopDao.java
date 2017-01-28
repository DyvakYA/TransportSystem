package model.dao;

import model.entities.Route;
import model.entities.Stop;

import java.util.List;
import java.util.Optional;

public interface StopDao extends GenericDao<Stop>{
    Optional<Stop> findById(int id);
    List<Stop> findAll() ;
    List<Stop> findAllStopsOnRoute(Route route);
}
