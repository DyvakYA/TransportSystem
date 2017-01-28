package model.dao.jdbc;

import model.dao.PlanOfStopsDao;
import model.dao.exception.DAOException;
import model.entities.Plan;
import model.entities.PlanOfStops;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPlanOfStopsDao implements PlanOfStopsDao {

    private static final String SELECT_FROM_PLAN_OF_STOPS_WHERE_PLAN_ID = "SELECT * FROM plan_of_stops WHERE plan_id=?";
    private static final String SELECT_FROM_PLAN_OF_STOPS_WHERE_PLAN_ID_AND_STOP_ID = "SELECT * FROM plan_of_stops WHERE plan_id=? AND stop_id=? ";
    private static final String SELECT_FROM_PLAN_OF_STOPS = "SELECT * FROM plan_of_stops";

    private static final String SELECT_FROM_PLAN_OF_STOPS_FOR_PLAN_QUERY = "SELECT  ss.* FROM schedule_of_stops ss, schedule s, route_stop rs  WHERE s.id=? AND s.id=ss.schedule_id "
            + "AND s.route_id=rs.route_id AND ss.stop_id=rs.stop_id ORDER BY rs.number ASC ";

    private static final String CREATE_PLAN_OF_STOPS_QUERY = "INSERT INTO plan_of_stops (stopId , planId, arriveTime, leaveTime)  VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PLAN_OF_STOPS_QUERY = "UPDATE plan_of_stops SET stop_id=?, plan_id=?, arriveTime=?, leaveTime=? WHERE plan_of_stops_id=?";
    private static final String DELETE_PLAN_OF_STOPS_QUERY = "DELETE FROM plan_of_stops WHERE plan_of_stops_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcPlanOfStopsDao() {}

    JdbcPlanOfStopsDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<PlanOfStops> findById(int id) throws DAOException {
        Optional<PlanOfStops> planOfStops = Optional.empty();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_PLAN_OF_STOPS_WHERE_PLAN_ID)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                planOfStops = Optional.of(getPlanOfStopsFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return planOfStops;
    }

    @Override
    public Optional<PlanOfStops> findById(int planId, int stopId)  {
        Optional<PlanOfStops> planOfStops = Optional.empty();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_PLAN_OF_STOPS_WHERE_PLAN_ID_AND_STOP_ID)){
            statement.setInt(1, planId);
            statement.setInt(2, stopId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                planOfStops = Optional.of(getPlanOfStopsFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return planOfStops;
    }

    @Override
    public List<PlanOfStops> findAll()  {
        List<PlanOfStops> planOfStops = new ArrayList<>();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_PLAN_OF_STOPS)){
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            planOfStops.add(getPlanOfStopsFromResultSet(result));
        }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return planOfStops;
    }

    @Override
    public void create(PlanOfStops planOfStops)  {
        try(PreparedStatement query = connection
                .prepareStatement(CREATE_PLAN_OF_STOPS_QUERY, Statement.RETURN_GENERATED_KEYS)){
            query.setInt( 1, planOfStops.getStopId());
            query.setInt( 2, planOfStops.getPlanId());
            query.setString( 3, planOfStops.getArriveTime());
            query.setString( 4, planOfStops.getLeaveTime());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                planOfStops.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public void update(PlanOfStops planOfStops, int id)  {
        try(PreparedStatement query = connection
                .prepareStatement(UPDATE_PLAN_OF_STOPS_QUERY, Statement.RETURN_GENERATED_KEYS)){
            query.setInt(1, planOfStops.getStopId());
            query.setInt(2, planOfStops.getPlanId());
            query.setString(3, planOfStops.getArriveTime());
            query.setString(4, planOfStops.getLeaveTime());
            query.setInt( 5, id);
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                planOfStops.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public void delete(int id)  {
        try(PreparedStatement statement = connection
                .prepareStatement(DELETE_PLAN_OF_STOPS_QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public List<Optional<PlanOfStops>> findPlanOfStopsForPlan(Plan plan)  {
        List<Optional<PlanOfStops>> planOfStops = new ArrayList<>();
        try(PreparedStatement query = connection
                .prepareStatement(SELECT_FROM_PLAN_OF_STOPS_FOR_PLAN_QUERY)){
            query.setInt(1, plan.getId());
            ResultSet result = query.executeQuery();
            while (result.next()) {
                planOfStops.add(Optional.of(getPlanOfStopsFromResultSet(result)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return planOfStops;
    }

    private PlanOfStops getPlanOfStopsFromResultSet(ResultSet rs) {
        PlanOfStops planOfStops = null;
        try {
            planOfStops = new PlanOfStops.Builder()
                    .setId(rs.getInt("planOfStops_id"))
                    .setStopId(rs.getInt("stop_id"))
                    .setPlanId(rs.getInt("plan_id"))
                    .setArriveTime(rs.getString("arrive"))
                    .setLeaveTime(rs.getString("leave"))
                    .build();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return planOfStops;
    }
}
