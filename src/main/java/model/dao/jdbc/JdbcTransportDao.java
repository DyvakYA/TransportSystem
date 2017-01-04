package model.dao.jdbc;

import model.dao.TransportDao;
import model.entities.enums.TransportType;
import model.entities.Transport;
import model.extras.Localization;
import model.extras.LoggerMaker;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcTransportDao implements TransportDao {

    private static final String SELECT_FROM_TRANSPORTS_WHERE_DRIVER_ID = "SELECT * FROM transports WHERE transport_id=?";
    private static final String SELECT_FROM_TRANSPORTS_WHERE_NAME = "SELECT * FROM transports WHERE number=?";
    private static final String SELECT_FROM_TRANSPORTS = "SELECT * FROM transports";

    private static final String CREATE_TRANSPORT_QUERY = "INSERT INTO transports (type, model, number)  VALUES (?, ?, ?)";
    private static final String UPDATE_TRANSPORT_QUERY = "UPDATE transports SET type=?, model=?, number=? WHERE transport_id=?";
    private static final String DELETE_TRANSPORT_QUERY = "DELETE FROM transports WHERE transport_id=?";

    private static final String SQL_EXCEPTION = "SQLException";

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
    public Transport findById(int id) {
        Transport transport = null;

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TRANSPORTS_WHERE_DRIVER_ID)) {

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                transport = new Transport(result.getInt(1),
                        TransportType.valueOf(result.getString(2).toUpperCase()),
                        result.getString(3),
                        result.getString(4));
            }

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transport;
    }

    @Override
    public List<Transport> findByNumber(String number) {
        List<Transport> transports = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TRANSPORTS_WHERE_NAME)) {

            statement.setString(1, number);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                transports.add (new Transport(result.getInt(1),
                        TransportType.valueOf(result.getString(2).toUpperCase()),
                        result.getString(3),
                        result.getString(4)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transports;
    }

    @Override
    public List<Transport> findAll() {
        List<Transport> transports = new ArrayList<>();

        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TRANSPORTS)) {

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                transports.add(new Transport(result.getInt(1),
                        TransportType.valueOf(result.getString(2).toUpperCase()),
                        result.getString(3),
                        result.getString(4)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transports;
    }

    @Override
    public void create(Transport transport) {

        try( PreparedStatement query =
                     connection.prepareStatement(CREATE_TRANSPORT_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , String.valueOf(transport.getType()));
            query.setString(2, transport.getModel());
            query.setString( 3 , transport.getNumber());
            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                transport.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void update(Transport transport, int id) {
        try( PreparedStatement query =
                     connection.prepareStatement(UPDATE_TRANSPORT_QUERY
                             , Statement.RETURN_GENERATED_KEYS ) ){
            query.setString( 1 , String.valueOf(transport.getType()));
            query.setString( 2 , transport.getModel());
            query.setString( 3 , transport.getNumber());
            query.setInt(4, id);

            query.executeUpdate();
            ResultSet keys =  query.getGeneratedKeys();
            if( keys.next()){
                transport.setId( keys.getInt(1) );
            }
        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection
                .prepareStatement(DELETE_TRANSPORT_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            Logger logger = LoggerMaker.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
    }
}
