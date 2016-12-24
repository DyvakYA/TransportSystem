package model.dao.jdbc;

import model.dao.TransportDao;
import model.entities.transports.Transport;
import model.entities.transports.UrbanTransport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcTransportDao implements TransportDao {

    private static final String SELECT_FROM_TRANSPORT = "SELECT * FROM transport";
    private static final String TRANSPORT_ID = "transport_id";
    private static final String NUMBER = "transport_number";
    private static final String INSERT_INTO_TRANSPORT_TRANSPORT_NUMBER_VALUES =
            "INSERT INTO transport (transport_number) VALUES ( ? )";
    private Connection connection;

    public JdbcTransportDao() {
    }

    JdbcTransportDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Transport> findByName(String name) {
        return null;
    }

    @Override
    public Transport find(int id) {
        return null;
    }

    @Override
    public List<Transport> findAll() {
        List<Transport> result = new ArrayList<>();
        try(Statement query =
                    connection.createStatement();
            ResultSet resultSet = query.executeQuery(SELECT_FROM_TRANSPORT)){

            while (resultSet.next()) {
                result.add( new UrbanTransport.Builder()

                        .setId(resultSet.getInt(TRANSPORT_ID))
                        .setNumber(resultSet.getString(NUMBER), resultSet.wasNull())
                        .build());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void create(Transport transport) {

        try( PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_TRANSPORT_TRANSPORT_NUMBER_VALUES
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , transport.getNumber());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                transport.setId( keys.getInt(1) );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Transport transport) {

    }

    @Override
    public void delete(int id) {

    }
}
