package model.dao.jdbc;

import model.dao.DriverDao;
import model.entities.drivers.Driver;
import model.entities.drivers.UrbanDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 24.12.2016.
 */
public class JdbcDriverDao implements DriverDao {

    private static final String SELECT_FROM_USERS = "SELECT * FROM drivers";
    private static final String USER_NAME = "drivers_name";
    private static final String USER_ID = "drivers_id";
    private static final String INSERT_INTO_DRIVERS = "INSERT INTO drivers (drivers_name) VALUES ( ? )";
    private Connection connection;

    public JdbcDriverDao() {}

    JdbcDriverDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Driver> findByName(String name) {
        return null;
    }

    @Override
    public Driver find(int id) {
        return null;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_FROM_USERS)){

            while (resultSet.next()) {
                result.add((Driver) new UrbanDriver.Builder()

                        .setId(resultSet.getInt(USER_ID))
                        .setName(resultSet.getString(USER_NAME), resultSet.wasNull())
                        .build());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void create(Driver driver) {

        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_DRIVERS
                             , Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, driver.getName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                driver.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Driver user) {

    }

    @Override
    public void delete(int id) {

    }

}
