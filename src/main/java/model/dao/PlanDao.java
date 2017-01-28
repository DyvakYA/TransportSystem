package model.dao;

import model.entities.Plan;
import model.entities.Route;

import java.util.List;
import java.util.Optional;


public interface PlanDao extends GenericDao<Plan>{
    Optional<Plan> findById(int id) ;
    List<Plan> findAll() ;
    int createAndGetGeneratedKey(Plan plan) ;

//    void updateTransport(Plan past_plan);

    void deleteFully(int id) ;

    List<Optional<Plan>> findPlansForRoute(Route route);
	
}
