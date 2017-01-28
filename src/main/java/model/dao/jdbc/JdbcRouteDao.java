package model.dao.jdbc;

import model.dao.RouteDao;
import model.dao.exception.DAOException;
import model.entities.Route;
import model.entities.Stop;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Route> findById(int id)  {
        Optional<Route> route = Optional.empty();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTES_WHERE_ROUTE_ID)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                route = Optional.of(getRouteFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return route;
    }

    @Override
    public List<Route> findAll() {
        List<Route> routes = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_FROM_ROUTES)){
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                routes.add(getRouteFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return routes;
    }

    @Override
    public void create(Route route) {
        try(PreparedStatement query = connection
                .prepareStatement(CREATE_ROUTE_QUERY, Statement.RETURN_GENERATED_KEYS )){
            query.setString( 1 , route.getName());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                route.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public void update(Route route, int id) {
        try(PreparedStatement query = connection
                .prepareStatement(UPDATE_ROUTE_QUERY, Statement.RETURN_GENERATED_KEYS)){
            query.setString( 1 , route.getName());
            query.setInt(2, id);
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                route.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);

        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection
                .prepareStatement(DELETE_ROUTE_QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public List<Optional<Route>> findRoutesByStops(Stop startStop, Stop finishStop) {
        List<Optional<Route>> routes = new ArrayList<>();
        try(PreparedStatement query = connection
                .prepareStatement(FIND_ROUTES_BY_STOPS_QUERY)){
            query.setString(1, startStop.getAddress());
            query.setString(2, finishStop.getAddress());
            ResultSet result = query.executeQuery();
            while (result.next()) {
                routes.add(Optional.of(getRouteFromResultSet(result)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return routes;
    }

    private Route getRouteFromResultSet(ResultSet rs) {
        Route route = null;
        try {
            route = new Route.Builder()
                    .setId(rs.getInt("route_id"))
                    .setName(rs.getString("name"))
                    .build();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return route;
    }
}
