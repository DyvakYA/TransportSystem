package model.dao.jdbc;

import model.dao.RouteStopsDao;
import model.entities.Route;
import model.entities.RouteStops;

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
    public Optional<RouteStops> findById(int id) throws SQLException {
        Optional<RouteStops> routeStops = Optional.empty();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTE_STOPS_WHERE_USER_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            routeStops = Optional.of(getRouteStopsFromResultSet(result));
        }
        return routeStops;
    }

    @Override
    public List<Optional<RouteStops>> findAll() throws SQLException {
        List<Optional<RouteStops>> routeStops = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_ROUTE_STOPS);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            routeStops.add(Optional.of(getRouteStopsFromResultSet(result)));
        }
        return routeStops;
    }

    @Override
    public void create(RouteStops routeStops) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(CREATE_ROUTE_STOPS_QUERY, Statement.RETURN_GENERATED_KEYS );
        query.setInt(1, routeStops.getRouteId());
        query.setInt(2, routeStops.getStopId());
        query.setInt(3 , routeStops.getNumber());
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            routeStops.setId( keys.getInt(1) );
        }
    }

    @Override
    public void update(RouteStops routeStops, int id) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(UPDATE_ROUTE_STOPS_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setInt(1, routeStops.getRouteId());
        query.setInt(2, routeStops.getStopId());
        query.setInt(3, routeStops.getNumber());
        query.setInt(4, id);
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            routeStops.setId( keys.getInt(1) );
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(DELETE_ROUTE_STOPS_QUERY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Optional<RouteStops>> findByRoute(Route route) throws SQLException {
        List<Optional<RouteStops>> routeStops = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ROUTE_STOPS_BY_ROUTE_QUERY);
        statement.setInt(1, route.getId());
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            routeStops.add(Optional.of(getRouteStopsFromResultSet(result)));
        }
        return routeStops;
    }

    private RouteStops getRouteStopsFromResultSet(ResultSet rs) throws SQLException {
        RouteStops routeStops = new RouteStops.Builder()
                .setId(rs.getInt("routStops_id"))
                .setRouteId(rs.getInt("routeId"))
                .setStopId(rs.getInt("stopId"))
                .setNumber(rs.getInt("number"))
                .build();
        return routeStops;
    }
}
