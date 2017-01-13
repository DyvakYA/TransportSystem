package dao;

import entities.Route;
import entities.Stop;

import java.util.List;

public interface RouteDao extends GenericDao<Route>{

    List<Route> findRoutesByStops(Stop startStop, Stop finishStop);
}
