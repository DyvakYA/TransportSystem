package model.dao;

import model.dao.exception.DAOException;
import model.entities.Plan;
import model.entities.PlanOfStops;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PlanOfStopsDao extends GenericDao<PlanOfStops>{
    Optional<PlanOfStops> findById(int id) throws SQLException, DAOException;
    Optional<PlanOfStops> findById(int planId, int stopId) throws SQLException, DAOException;
    List<PlanOfStops> findAll() throws SQLException, DAOException;
    List<Optional<PlanOfStops>> findPlanOfStopsForPlan(Plan plan) throws SQLException, DAOException;

}
