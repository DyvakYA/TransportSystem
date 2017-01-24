package model.dao;

import model.entities.Plan;
import model.entities.Route;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface PlanDao extends GenericDao<Plan>{
    Optional<Plan> findById(int id) throws SQLException;
    List<Optional<Plan>> findAll() throws SQLException;
    int createAndGetGeneratedKey(Plan plan) throws SQLException;

//    void updateTransport(Plan past_plan);

    void deleteFully(int id) throws SQLException;

    List<Optional<Plan>> findPlansForRoute(Route route) throws SQLException;
	
}
