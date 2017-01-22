package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.PlanDao;
import model.entities.Plan;

import java.util.List;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class PlanService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final PlanService INSTANCE = new PlanService();
    }

    public static PlanService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Plan> getAllPlans() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);
            //DaoFactory.getInstance()
            return planDao.findAll();
        }
    }

    public void createPlan(Plan plan) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);

            planDao.create(plan);
        }
    }

    public void deletePlan(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);
            planDao.delete(id);
        }
    }
}
