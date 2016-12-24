package model.dao;

import model.entities.transports.Transport;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface TransportDao extends GenericDao<Transport> {
    List<Transport> findByName (String name);
}
