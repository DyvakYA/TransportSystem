package dao.jdbc;

import dao.RouteDao;
import entities.Route;
import entities.Stop;
import extras.Localization;
import extras.LoggerMaker;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRouteDao implements RouteDao {

    private static final String SELECT_FROM_ROUTES_WHERE_ROUTE_ID = "SELECT * FROM routes WHERE route_id=?";
    private static final String SELECT_FROM_ROUTES = "SELECT * FROM routes";

    private static final String FIND_ROUTES_BY_STOPS_QUERY = "SELECT DISTINCT r.id, r.name FROM route r, route_stop rs, stop s WHERE r.id=rs.route_id "
            + "AND rs.stop_id=s.id AND (s.name=? OR  s.name=? )";

    private static final String CREATE_ROUTE_QUERY = "INSERT INTO routes (name)  VALUES (?)";
    private static final String UPDATE_ROUTE_QUERY = "UPDATE users SET name=? WHERE route_id=?";
    private static final String DELETE_ROUTE_QUERY = "DELETE FROM routes WHERE route_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcRouteDao() {}

    JdbcRouteDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Route findById(int id) {

        Route route = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTES_WHERE_ROUTE_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                route = new Route(result.getInt(1),
                        result.getString(2));
            }

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return route;
    }

    @Override
    public List<Route> findAll() {

        List<Route> routes = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTES)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                routes.add(new Route(result.getInt(1),
                        result.getString(2)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routes;
    }

    @Override
    public void create(Route route) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_ROUTE_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , route.getName());

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                route.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(Route route, int id) {

        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_ROUTE_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , route.getName());

            query.setInt(2, id);

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                route.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {

        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_ROUTE_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public List<Route> findRoutesByStops(Stop startStop, Stop finishStop) {

        List<Route> routes = new ArrayList<>();

        try (PreparedStatement query = connection
                .prepareStatement(FIND_ROUTES_BY_STOPS_QUERY)) {

            query.setString(1, startStop.getAddress());
            query.setString(2, finishStop.getAddress());

            ResultSet result = query.executeQuery();

            while (result.next()) {
                routes.add(new Route(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException e) {
            Logger logger =  LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return routes;
    }
}
