package dao;

import entities.Plan;
import entities.Route;

import java.util.List;


public interface PlanDao extends GenericDao<Plan>{

    int createAndGetGeneratedKey(Plan plan);

//    void updateTransport(Plan plan);

    void deleteFully(int id);

    List<Plan> findPlansForRoute(Route route);
	
}
