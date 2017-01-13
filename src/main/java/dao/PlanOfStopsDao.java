package dao;

import entities.Plan;
import entities.PlanOfStops;

import java.util.List;

public interface PlanOfStopsDao extends GenericDao<PlanOfStops>{

    List<PlanOfStops> findPlanOfStopsForPlan(Plan plan);
}
