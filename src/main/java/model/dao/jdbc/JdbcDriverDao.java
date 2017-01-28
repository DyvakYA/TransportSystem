package model.dao.jdbc;

import model.dao.DriverDao;
import model.dao.exception.DAOException;
import model.entities.Driver;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

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
    public Optional<Driver> findById(int id) {
        Optional<Driver> driver = Optional.empty();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS_WHERE_DRIVER_ID)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                driver = Optional.of(getDriverFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return driver;
    }

    @Override
    public List<Driver> findAll()  {
        List<Driver> drivers = new ArrayList<>();
        try(PreparedStatement statement = connection.
                prepareStatement(SELECT_FROM_DRIVERS)){
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                drivers.add(getDriverFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return drivers;
    }

    @Override
    public void create(Driver driver) {
        try(PreparedStatement query = connection
                .prepareStatement(CREATE_DRIVER_QUERY, Statement.RETURN_GENERATED_KEYS)){
            System.out.println(driver);
            query.setString( 1 , driver.getName());
            query.setString(2, driver.getSurname());
            query.setInt(3, driver.getAge());
            query.setInt(4, driver.getRouteId());
            System.out.println(query);
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                driver.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public void update(Driver driver, int id) {
        try(PreparedStatement query = connection
                .prepareStatement(UPDATE_DRIVER_QUERY, Statement.RETURN_GENERATED_KEYS)){
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
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement query = connection
                .prepareStatement(DELETE_DRIVER_QUERY)){
            System.out.println(id);
            query.setInt(1, id);
            System.out.println(query);
            query.executeUpdate();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
    }

    @Override
    public List<Optional<Driver>> findByName(String name) {
        List<Optional<Driver>> drivers = new ArrayList<>();
        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_DRIVERS_WHERE_NAME)){
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                drivers.add(Optional.of(getDriverFromResultSet(result)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return drivers;
    }

    private Driver getDriverFromResultSet(ResultSet rs){
        Driver driver = null;
        try {
            driver = new Driver.Builder()
                    .setId(rs.getInt("driver_id"))
                    .setName(rs.getString("name"))
                    .setSurname(rs.getString("surname"))
                    .setAge(rs.getInt("age"))
                    .setRouteId(rs.getInt("route_id"))
                    .build();
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
            throw new DAOException("dao exception occured when retrieving category by title", e);
        }
        return driver;
    }
}
