package model.dao;

import model.entities.Plan;
import model.entities.Route;
import model.entities.Transport;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface TransportDao extends GenericDao<Transport> {
    Optional<Transport> findById(int id);
    List<Optional<Transport>> findAll() throws SQLException;
    List<Optional<Transport>> findByNumber(String number) throws SQLException;
    List<Optional<Transport>> findTransportOnRoute(Route route);
    Optional<Transport> findTransportOnPlan(Plan plan) throws SQLException;
}
