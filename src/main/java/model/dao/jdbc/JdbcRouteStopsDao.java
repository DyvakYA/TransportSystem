package model.dao.jdbc;

import model.dao.RouteStopsDao;
import model.dao.exception.DAOException;
import model.entities.Route;
import model.entities.RouteStops;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcRouteStopsDao implements RouteStopsDao {

    private static final String SELECT_FROM_ROUTE_STOPS_WHERE_USER_ID = "SELECT * FROM route_stops WHERE route_stops_id=?";
    private static final String SELECT_FROM_ROUTE_STOPS = "SELECT * FROM route_stops";

    private static final String SELECT_ROUTE_STOPS_BY_ROUTE_QUERY = "SELECT * FROM route_stop WHERE route_id=? ORDER BY number ASC";

    private static final String CREATE_ROUTE_STOPS_QUERY = "INSERT INTO route_stops (route_id , stop_id, number)  VALUES (?, ?, ?)";
    private static final String UPDATE_ROUTE_STOPS_QUERY = "UPDATE route_stops SET route_id=?, stop_id=?, number=? WHERE route_stops_id=?";
    private static final String DELETE_ROUTE_STOPS_QUERY = "DELETE FROM route_stops WHERE route_stops_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcRouteStopsDao() {}

    JdbcRouteStopsDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<RouteStops> findById(int id) {
        Optional<RouteStops> routeStops = Optional.empty();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTE_STOPS_WHERE_USER_ID)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                routeStops = Optional.of(getRouteStopsFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return routeStops;
    }

    @Override
    public List<RouteStops> findAll(){
        List<RouteStops> routeStops = new ArrayList<>();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTE_STOPS)){
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                routeStops.add(getRouteStopsFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return routeStops;
    }

    @Override
    public void create(RouteStops routeStops)  {
        try(PreparedStatement query = connection
                .prepareStatement(CREATE_ROUTE_STOPS_QUERY, Statement.RETURN_GENERATED_KEYS )){
            query.setInt(1, routeStops.getRouteId());
            query.setInt(2, routeStops.getStopId());
            query.setInt(3 , routeStops.getNumber());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                routeStops.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public void update(RouteStops routeStops, int id)  {
        try(PreparedStatement query = connection
                .prepareStatement(UPDATE_ROUTE_STOPS_QUERY, Statement.RETURN_GENERATED_KEYS)){
            query.setInt(1, routeStops.getRouteId());
            query.setInt(2, routeStops.getStopId());
            query.setInt(3, routeStops.getNumber());
            query.setInt(4, id);
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                routeStops.setId( keys.getInt(1) );
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
                .prepareStatement(DELETE_ROUTE_STOPS_QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public List<Optional<RouteStops>> findByRoute(Route route)  {
        List<Optional<RouteStops>> routeStops = new ArrayList<>();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_ROUTE_STOPS_BY_ROUTE_QUERY)){
            statement.setInt(1, route.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                routeStops.add(Optional.of(getRouteStopsFromResultSet(result)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return routeStops;
    }

    private RouteStops getRouteStopsFromResultSet(ResultSet rs) {
        RouteStops routeStops = null;
        try {
            routeStops = new RouteStops.Builder()
                    .setId(rs.getInt("routStops_id"))
                    .setRouteId(rs.getInt("routeId"))
                    .setStopId(rs.getInt("stopId"))
                    .setNumber(rs.getInt("number"))
                    .build();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return routeStops;
    }
}
