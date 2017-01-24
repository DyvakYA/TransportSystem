package model.dao.jdbc;

import model.dao.TransportDao;
import model.entities.Plan;
import model.entities.Route;
import model.entities.Transport;
import model.entities.enums.TransportType;
import model.extras.Localization;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcTransportDao implements TransportDao {

    private static final String SELECT_FROM_TRANSPORTS_WHERE_DRIVER_ID = "SELECT * FROM transports WHERE transport_id=?";
    private static final String FIND_TRANSPORT_ON_ROOT_QUERY = "SELECT t.id, t.type, t.number, t.model FROM route r, plan sc,transport t WHERE r.id=? " +
            "AND r.id=sc.route_id AND sc.transport_id=t.id";
    private static final String FIND_TRANSPORT_ON_PLAN_QUERY = "SELECT t.id, t.type, t.number, t.model FROM plan sc, transport t WHERE sc.id=? " +
            "AND sc.transport_id=t.id";
    private static final String SELECT_FROM_TRANSPORTS = "SELECT * FROM transports";

    private static final String SELECT_FROM_TRANSPORTS_WHERE_NAME = "SELECT * FROM transports WHERE number=?";

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
    public Optional<Transport> findById(int id) {
        Optional<Transport> transport = Optional.empty();
        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TRANSPORTS_WHERE_DRIVER_ID)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                transport = Optional.of(getTransportFromResultSet(result));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transport;
    }

    @Override
    public List<Optional<Transport>> findTransportOnRoute(Route route) {
        List<Optional<Transport>> transports = new LinkedList<>();
        try (PreparedStatement query = connection
                .prepareStatement(FIND_TRANSPORT_ON_ROOT_QUERY)) {
            query.setInt(1, route.getId());
            ResultSet result = query.executeQuery();
            while (result.next()) {
                transports.add(Optional.of(getTransportFromResultSet(result)));
            }
        } catch (SQLException e) {
            Logger logger = LoggerHelper.getInstance().getLogger();
            logger.error(Localization.getInstanse().getLocalizedErrorMsg(SQL_EXCEPTION), e);
        }
        return transports;
    }

    @Override
    public Optional<Transport> findTransportOnPlan(Plan plan) throws SQLException {
        Optional<Transport> transport = Optional.empty();
        PreparedStatement query = connection
                .prepareStatement(FIND_TRANSPORT_ON_PLAN_QUERY);
        query.setInt(1, plan.getId());
        ResultSet result = query.executeQuery();
        while (result.next()) {
            transport = Optional.of(getTransportFromResultSet(result));
        }
        return transport;
    }

    @Override
    public List<Optional<Transport>> findAll() throws SQLException {
        List<Optional<Transport>> transports = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TRANSPORTS);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            transports.add(Optional.of(getTransportFromResultSet(result)));
        }
        return transports;
    }

    @Override
    public void create(Transport transport) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(CREATE_TRANSPORT_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setString( 1 , String.valueOf(transport.getType()));
        query.setString(2, transport.getModel());
        query.setString( 3 , transport.getNumber());
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            transport.setId( keys.getInt(1) );
        }
    }

    @Override
    public void update(Transport transport, int id) throws SQLException {
        PreparedStatement query = connection
                .prepareStatement(UPDATE_TRANSPORT_QUERY, Statement.RETURN_GENERATED_KEYS);
        query.setString( 1 , String.valueOf(transport.getType()));
        query.setString( 2 , transport.getModel());
        query.setString( 3 , transport.getNumber());
        query.setInt(4, id);
        query.executeUpdate();
        ResultSet keys =  query.getGeneratedKeys();
        if( keys.next()){
            transport.setId( keys.getInt(1) );
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_TRANSPORT_QUERY);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<Optional<Transport>> findByNumber(String number) throws SQLException {
        List<Optional<Transport>> transports = new ArrayList<>();
        PreparedStatement statement = connection
                .prepareStatement(SELECT_FROM_TRANSPORTS_WHERE_NAME);
        statement.setString(1, number);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            transports.add(Optional.of(getTransportFromResultSet(result)));
        }
        return transports;
    }

    private Transport getTransportFromResultSet(ResultSet rs) throws SQLException {
        Transport transport = new Transport.Builder()
                .setId(rs.getInt("transport_id"))
                .setType(TransportType.valueOf(rs.getString("type")))
                .setNumber(rs.getString("number"))
                .setModel(rs.getString("model"))
                .build();
        return transport;
    }
}
