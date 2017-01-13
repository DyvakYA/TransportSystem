package dao;

import entities.Transport;

import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public interface TransportDao extends GenericDao<Transport> {
    List<Transport> findByNumber (String number);
}
