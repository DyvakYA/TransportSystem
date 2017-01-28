package model.dao;

import model.entities.Driver;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public interface DriverDao extends GenericDao<Driver>{
    Optional<Driver> findById(int id) ;
    List<Driver> findAll();
    List<Optional<Driver>> findByName(String name);
}