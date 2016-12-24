package model.dao;

import model.entities.drivers.Driver;

import java.util.List;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface DriverDao extends GenericDao<Driver>{

    List<Driver> findByName(String name);
}