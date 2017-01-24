package model.dao.jdbc;

import model.dao.DriverDao;
import model.entities.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcDriverDao implements DriverDao {

    private static final String SELECT_FROM_DRIVERS_WHERE_DRIVER_ID = "SELECT * FROM drivers WHERE driver_id=?";
    private static final String SELECT_FROM_DRIVERS = "SELECT * FROM drivers";

    private static final String SELECT_FROM_DRIVERS_WHERE_NAME = "SELECT * FROM drivers WHERE name=?";

    private static final String CREATE_DRIVER_QUERY = "INSERT INTO drivers (name, surname, age, route_id)  VALUES (?, ?, ?, ?)";
    private static final String UPDATE_DRIVER_QUERY = "UPDATE drivers SET name=?, surname=?, age=? WHERE driver_id=?";
    private static final String DELETE_DRIVER_QUERY = "DELETE FROM drivers WHERE driver_id=?";

    private Connection connection;

    public JdbcDriverDao() {}

    JdbcDriverDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Driver> findById(int id) throws SQLException {
        Optional<Driver> driver = Optional.empty();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS_WHERE_DRIVER_ID);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            driver = Optional.of(getDriverFromResultSet(result));
        }
        return driver;
    }

    @Override
    public List<Optional<Driver>> findAll() throws SQLException {
        List<Optional<Driver>> drivers = new ArrayList<>();
        PreparedStatement statement = connection.
                prepareStatement(SELECT_FROM_DRIVERS);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            drivers.add(Optional.of(getDriverFromResultSet(result)));
        }
        return drivers;
    }

    @Override
    public void create(Driver driver) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(CREATE_DRIVER_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setString( 1 , driver.getName());
        query.setString(2, driver.getSurname());
        query.setInt(3, driver.getAge());
        query.setInt(4, driver.getRouteId());
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            driver.setId( keys.getInt(1) );
        }
    }

    @Override
    public void update(Driver driver, int id) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(UPDATE_DRIVER_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setString( 1 , driver.getName());
        query.setString( 2 , driver.getSurname());
        query.setInt(3, driver.getAge());
        query.setInt(4, id);
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            driver.setId( keys.getInt(1) );
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(DELETE_DRIVER_QUERY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Optional<Driver>> findByName(String name) throws SQLException {
        List<Optional<Driver>> drivers = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS_WHERE_NAME);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            drivers.add(Optional.of(getDriverFromResultSet(result)));
        }
        return drivers;
    }

    private Driver getDriverFromResultSet(ResultSet rs) throws SQLException {
        Driver driver = new Driver.Builder()
                .setId(rs.getInt("driver_id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setAge(rs.getInt("age"))
                .build();
        return driver;
    }
}
