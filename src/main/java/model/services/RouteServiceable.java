package model.services;

import model.entities.Route;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface RouteServiceable extends GenericService<Route> {
    List<Optional<Route>> getAll() throws SQLException;
}
