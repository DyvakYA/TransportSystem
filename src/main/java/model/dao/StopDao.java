package model.dao;

import model.entities.Route;
import model.entities.Stop;

import java.util.List;

public interface StopDao extends GenericDao<Stop>{

    List<Stop> findAllStopsOnRoute(Route route);
}
