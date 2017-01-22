package model.dao.jdbc;

import model.dao.DriverDao;
import model.entities.Driver;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    private static final String SQL_EXCEPTION = "SQLException";

    private Connection connection;

    public JdbcDriverDao() {}

    JdbcDriverDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Driver findById(int id) {
        Driver driver = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS_WHERE_DRIVER_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                driver = new Driver(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5));
            }

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return driver;
    }

    @Override
    public List<Driver> findAll() {
        Driver driver = null;
        List<Driver> drivers = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                drivers.add(new Driver(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return drivers;
    }

    @Override
    public void create(Driver driver) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_DRIVER_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , driver.getName());
            query.setString(2, driver.getSurname());
            query.setInt(3, driver.getAge());
            query.setInt(4, driver.getRouteId());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                driver.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(Driver driver, int id) {
        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_DRIVER_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , driver.getName());
            query.setString( 2 , driver.getSurname());
            query.setInt(3, driver.getAge());

            query.setInt(4, id);

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                driver.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_DRIVER_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public List<Driver> findByName(String name) {
        List<Driver> drivers = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS_WHERE_NAME)) {

            statement.setString(1, name);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                drivers.add(new Driver(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getInt(5)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return drivers;
    }
}
