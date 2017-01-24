package model.dao.jdbc;

import model.dao.StopDao;
import model.entities.Route;
import model.entities.Stop;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Stop> findById(int id) throws SQLException {
        Optional<Stop> stop = Optional.empty();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_STOPS_WHERE_STOP_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            stop = Optional.of(getStopFromResultSet(result));
        }
        return stop;
    }

    @Override
    public List<Optional<Stop>> findAll() throws SQLException {
        List<Optional<Stop>> stops = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_STOPS);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            stops.add(Optional.of(getStopFromResultSet(result)));
        }
        return stops;
    }

    @Override
    public void create(Stop stop) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(CREATE_STOP_QUERY, Statement.RETURN_GENERATED_KEYS );
        query.setString( 1 , stop.getName());
        query.setString( 2 , stop.getAddress());
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            stop.setId( keys.getInt(1) );
        }
    }

    @Override
    public void update(Stop stop, int id) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(UPDATE_STOP_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setString( 1 , stop.getName());
        query.setString( 2 , stop.getAddress());
        query.setInt(3, id);
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            stop.setId( keys.getInt(1) );
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(DELETE_STOP_QUERY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Optional<Stop>> findAllStopsOnRoute(Route route) throws SQLException {
        List<Optional<Stop>> stops = new LinkedList<>();
        PreparedStatement query = connection
                .prepareStatement(SELECT_STOPS_FROM_ROUTE);
        query.setInt(1, route.getId());
        ResultSet result = query.executeQuery();
        while (result.next()) {
            stops.add(Optional.of(getStopFromResultSet(result)));
        }
        return stops;
    }

    private Stop getStopFromResultSet(ResultSet rs) throws SQLException {
        Stop stop = new Stop.Builder()
                .setId(rs.getInt("stop_id"))
                .setName(rs.getString("name"))
                .setAddress(rs.getString("address"))
                .build();
        return stop;
    }
}
