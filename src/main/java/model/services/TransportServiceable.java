package model.services;

import model.entities.Transport;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 23.01.2017.
 */
public interface TransportServiceable extends GenericService<Transport> {
    List<Optional<Transport>> getAll() throws SQLException;
}
