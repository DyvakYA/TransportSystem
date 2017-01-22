package model.dao;

import model.entities.Plan;
import model.entities.Route;
import model.entities.Transport;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface TransportDao extends GenericDao<Transport> {
    List<Transport> findByNumber (String number);
    List<Transport> findTransportOnRoute(Route route);
    Transport findTransportOnPlan(Plan plan);
}
