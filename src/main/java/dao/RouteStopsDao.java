package dao;

import entities.Route;
import entities.RouteStops;

import java.util.List;

public interface RouteStopsDao extends GenericDao<RouteStops>{

    List<RouteStops> findByRoute(Route route);

}
