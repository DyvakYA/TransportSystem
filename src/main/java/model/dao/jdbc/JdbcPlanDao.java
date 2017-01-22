package model.dao.jdbc;

import model.dao.PlanDao;
import model.entities.Plan;
import model.entities.Route;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JdbcPlanDao implements PlanDao {

    private static final String SELECT_FROM_PLANS_WHERE_PLAN_ID = "SELECT * FROM plans WHERE plan_id=?";
    private static final String SELECT_FROM_PLANS = "SELECT * FROM plans";

    private static final String SELECT_PLANS_FOR_ROUTE_QUERY = "SELECT * FROM plans WHERE route_id=?;";

    private static final String CREATE_PLAN_QUERY = "INSERT INTO plans (route_id , transport_id)  VALUES (?, ?)";
    private static final String UPDATE_PLAN_QUERY = "UPDATE plans SET route_id=?, transport_id=? WHERE plan_id=?";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plans WHERE plan_id=?";

    private static final String DELETE_PLAN_OF_STOPS_QUERY = "DELETE FROM  plan_of_stops  WHERE  plan_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcPlanDao() {}

    JdbcPlanDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Plan findById(int id) {

        Plan plan = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_PLANS_WHERE_PLAN_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                plan = new Plan(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3));
            }

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return plan;
    }

    @Override
    public List<Plan> findAll() {

        List<Plan> plans = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_PLANS)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                plans.add(new Plan(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3)));

            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return plans;
    }

    @Override
    public void create(Plan plan) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_PLAN_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setInt(1, plan.getRouteId());
            query.setInt(2, plan.getTransportId());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                plan.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(Plan plan, int id) {

        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_PLAN_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setInt(1, plan.getRouteId());
            query.setInt(2, plan.getTransportId());

            query.setInt(3, id);

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                plan.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {

        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_PLAN_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public int createAndGetGeneratedKey(Plan plan) {

        Integer key = null;

        try (PreparedStatement statement = connection.prepareStatement(CREATE_PLAN_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, plan.getRouteId());
            statement.setInt(2, plan.getTransportId());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            while (result.next()) {
                key = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), ex);
        }
        return key;
    }

//    @Override
//    public void updateTransport(Plan past_plan) {
//        try( PreparedStatement query =
//                     connection.prepareStatement(UPDATE_PLAN_QUERY
//                             , Statement.RETURN_GENERATED_KEYS ) ){
//            query.setInt(1, past_plan.getRouteId());
//            query.setInt(2, past_plan.getTransportId());
//
//            query.executeUpdate();
//            ResultSet keys =  query.getGeneratedKeys();
//            if( keys.next()){
//                past_plan.setId( keys.getInt(1) );
//            }
//        } catch (SQLException e) {
//            Logger logger = LoggerMaker.getInstance().getLogger();
//            logger.error(Localization.getInstance().getLocalizedErrorMsg(SQL_EXCEPTION), e);
//        }
//    }

    @Override
    public void deleteFully(int id) {

        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_PLAN_OF_STOPS_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public List<Plan> findPlansForRoute(Route route) {

        List<Plan> plans = new LinkedList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_PLANS_FOR_ROUTE_QUERY)) {

            statement.setInt(1, route.getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                plans.add(new Plan(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return plans;
    }
}
