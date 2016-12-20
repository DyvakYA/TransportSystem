package model.dao;

import model.entities.transport.Bus;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface BusDao extends GenericDao<Bus> {
    List<Bus> findByName (String name);

}
