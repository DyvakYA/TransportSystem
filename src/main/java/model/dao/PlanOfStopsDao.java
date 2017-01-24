package model.dao;

import model.entities.Plan;
import model.entities.PlanOfStops;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PlanOfStopsDao extends GenericDao<PlanOfStops>{
    Optional<PlanOfStops> findById(int id) throws SQLException;
    Optional<PlanOfStops> findById(int planId, int stopId) throws SQLException;
    List<Optional<PlanOfStops>> findAll() throws SQLException;
    List<Optional<PlanOfStops>> findPlanOfStopsForPlan(Plan plan) throws SQLException;

}
