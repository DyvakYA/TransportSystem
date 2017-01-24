package model.services.service;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.PlanDao;
import model.entities.Plan;
import model.services.PlanServiceable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 21.01.2017.
 */
public class PlanService implements PlanServiceable {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final PlanService INSTANCE = new PlanService();
    }

    public static PlanService getInstance() {
        return Holder.INSTANCE;
    }

    public List<Optional<Plan>> getAll() throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);
            return planDao.findAll();
        }
    }

    public void create(Plan plan) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);
            planDao.create(plan);
            connection.commit();
        }
    }

    @Override
    public void update(Plan plan, int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);
            planDao.update(plan, id);
            connection.commit();
        }

    }

    public void delete(int id) throws SQLException {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PlanDao planDao = daoFactory.createPlanDao(connection);
            planDao.delete(id);
            connection.commit();
        }
    }
}
