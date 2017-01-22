package model.dao.jdbc;

import model.dao.StopDao;
import model.entities.Route;
import model.entities.Stop;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JdbcStopDao implements StopDao {

    private static final String SELECT_FROM_STOPS_WHERE_STOP_ID = "SELECT * FROM stops WHERE stop_id=?";
    private static final String SELECT_FROM_STOPS = "SELECT * FROM stops";

    private static final String SELECT_STOPS_FROM_ROUTE = "SELECT  s.id, s.name, s.address FROM route r , route_stop rs  "
            + ", stops WHERE r.id=rs.route_id AND s.id=rs.stop_id AND r.id=? ORDER BY rs.number ASC";

    private static final String CREATE_STOP_QUERY = "INSERT INTO stops (name, address)  VALUES (?, ?)";
    private static final String UPDATE_STOP_QUERY = "UPDATE stops SET name=?, address=?  WHERE id=?";
    private static final String DELETE_STOP_QUERY = "DELETE FROM stops WHERE stop_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcStopDao() {}

    JdbcStopDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stop findById(int id) {

        Stop stop = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_STOPS_WHERE_STOP_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                stop = new Stop(result.getInt(1),
                        result.getString(2),
                        result.getString(3));

            }

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return stop;
    }

    @Override
    public List<Stop> findAll() {

        List<Stop> stops = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_STOPS)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                stops.add(new Stop(result.getInt(1),
                        result.getString(2),
                        result.getString(3)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return stops;
    }

    @Override
    public void create(Stop stop) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_STOP_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , stop.getName());
            query.setString( 2 , stop.getAddress());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                stop.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(Stop stop, int id) {

        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_STOP_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , stop.getName());
            query.setString( 2 , stop.getAddress());

            query.setInt(3, id);

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                stop.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {

        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_STOP_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public List<Stop> findAllStopsOnRoute(Route route) {

        List<Stop> stops = new LinkedList<>();

        try (PreparedStatement query = connection
                .prepareStatement(SELECT_STOPS_FROM_ROUTE)) {

            query.setInt(1, route.getId());

            ResultSet result = query.executeQuery();

            while (result.next()) {

                stops.add(new Stop(result.getInt(1), result.getString(2),
                        result.getString(3)));
            }

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return stops;

    }
}
