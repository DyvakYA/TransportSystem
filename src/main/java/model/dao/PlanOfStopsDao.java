package model.dao;

import model.entities.Plan;
import model.entities.PlanOfStops;

import java.util.List;

public interface PlanOfStopsDao extends GenericDao<PlanOfStops>{

    List<PlanOfStops> findPlanOfStopsForPlan(Plan plan);

    PlanOfStops findById(int planId, int stopId);
}
