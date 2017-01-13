package dao.jdbc;

import dao.RouteStopsDao;
import entities.Route;
import entities.RouteStops;
import extras.Localization;
import extras.LoggerMaker;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public RouteStops findById(int id) {

        RouteStops routeStops = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTE_STOPS_WHERE_USER_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                routeStops = new RouteStops(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4));
            }

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routeStops;
    }

    @Override
    public List<RouteStops> findAll() {

        List<RouteStops> routeStops = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTE_STOPS)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                routeStops.add(new RouteStops(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routeStops;
    }

    @Override
    public void create(RouteStops routeStops) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_ROUTE_STOPS_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setInt(1, routeStops.getRouteId());
            query.setInt(2, routeStops.getStopId());
            query.setInt( 3 , routeStops.getNumber());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                routeStops.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(RouteStops routeStops, int id) {

        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_ROUTE_STOPS_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
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
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_ROUTE_STOPS_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }

    }

    @Override
    public List<RouteStops> findByRoute(Route route) {

        List<RouteStops> routeStops = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_ROUTE_STOPS_BY_ROUTE_QUERY)) {

            statement.setInt(1, route.getId());

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                routeStops.add(new RouteStops(result.getInt(1), result.getInt(2),
                        result.getInt(3), result.getInt(4)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routeStops;
    }
}
