package model.dao;

import model.entities.Route;
import model.entities.Stop;

import java.util.List;

public interface RouteDao extends GenericDao<Route>{

    List<Route> findRoutesByStops(Stop startStop, Stop finishStop);
}
