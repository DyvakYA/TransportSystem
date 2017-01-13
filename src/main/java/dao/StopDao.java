package dao;

import entities.Route;
import entities.Stop;

import java.util.List;

public interface StopDao extends GenericDao<Stop>{

    List<Stop> findAllStopsOnRoute(Route route);
}
