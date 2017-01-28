package model.dao;


import model.entities.Route;
import model.entities.Stop;

import java.util.List;
import java.util.Optional;

public interface RouteDao extends GenericDao<Route>{
    Optional<Route> findById(int id) ;
    List<Route> findAll() ;
    List<Optional<Route>> findRoutesByStops(Stop startStop, Stop finishStop);
}
