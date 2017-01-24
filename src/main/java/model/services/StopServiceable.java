package model.services;

import model.entities.Stop;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface StopServiceable extends GenericService<Stop> {
    List<Optional<Stop>> getAll() throws SQLException;
}
