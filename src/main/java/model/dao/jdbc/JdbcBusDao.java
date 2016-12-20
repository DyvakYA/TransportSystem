package model.dao.jdbc;

import model.dao.BusDao;
import model.entities.transport.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcBusDao implements BusDao {

    private static final String SELECT_FROM_BUS = "SELECT * FROM bus";
    private static final String CITY_ID = "bus_id";
    private static final String NAME = "bus_number";
    private Connection connection;

    public JdbcBusDao() {
    }

    JdbcBusDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Bus> findByName(String name) {
        return null;
    }

    @Override
    public Bus find(int id) {
        return null;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_FROM_BUS)){

            while (resultSet.next()) {
                result.add( new Bus.Builder()

                        .setId(resultSet.getInt(CITY_ID))
                        .setNumber(resultSet.getString(NAME), resultSet.wasNull())
                        .build());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void create(Bus bus) {

        try( PreparedStatement query =
                     connection.prepareStatement("INSERT INTO bus (bus_number) VALUES ( ? )"
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , bus.getNumber());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                bus.setId( keys.getInt(1) );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Bus bus) {

    }

    @Override
    public void delete(int id) {

    }
}
