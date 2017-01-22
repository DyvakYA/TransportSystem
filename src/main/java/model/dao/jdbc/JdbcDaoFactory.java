package model.dao.jdbc;

import model.dao.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;
    private static final String DB_URL = "url";

    private DataSource dataSource;

    public JdbcDaoFactory() {

        try{

//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//            InputStream inputStream =
//                    DaoFactory.class.getResourceAsStream(DB_FILE);
//            Properties dbProps = new Properties();
//            dbProps.load(inputStream);
//            String url = dbProps.getProperty(DB_URL);
//            connection = DriverManager.getConnection(url, dbProps);

            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/mydb");

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection( dataSource.getConnection() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TransportDao createTransportDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcTransportDao( sqlConnection );
    }

    @Override
    public DriverDao createDriverDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcDriverDao( sqlConnection );
    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserDao( sqlConnection );
    }

    @Override
    public RouteDao createRouteDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcRouteDao( sqlConnection );
    }

    @Override
    public StopDao createStopDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcStopDao( sqlConnection );
    }

    @Override
    public PlanDao createPlanDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcPlanDao( sqlConnection );
    }

    @Override
    public PlanOfStopsDao createPlanOfStopsDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcPlanOfStopsDao( sqlConnection );
    }

    @Override
    public RouteStopsDao createRouteStopsDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection)connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcRouteStopsDao( sqlConnection );
    }
}
