package model.dao;

import model.entities.Route;
import model.entities.RouteStops;

import java.util.List;

public interface RouteStopsDao extends GenericDao<RouteStops>{

    List<RouteStops> findByRoute(Route route);

}
