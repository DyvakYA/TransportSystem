package model.dao;

import model.entities.Plan;
import model.entities.Route;

import java.util.List;


public interface PlanDao extends GenericDao<Plan>{

    int createAndGetGeneratedKey(Plan plan);

//    void updateTransport(Plan past_plan);

    void deleteFully(int id);

    List<Plan> findPlansForRoute(Route route);
	
}
