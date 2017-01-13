package dao.jdbc;

import dao.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Dyvak on 17.12.2016.
 */
public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;
    private static final String DB_URL = "url";

    public JdbcDaoFactory() {
        try{

            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            InputStream inputStream =
                    DaoFactory.class.getResourceAsStream(DB_FILE);
            Properties dbProps = new Properties();
            dbProps.load(inputStream);
            String url = dbProps.getProperty(DB_URL);
            connection = DriverManager.getConnection(url, dbProps);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public TransportDao createTransportDao() {
        return new JdbcTransportDao(connection);
    }

    @Override
    public DriverDao createDriverDao() {
        return new JdbcDriverDao(connection);
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(connection);
    }

    @Override
    public RouteDao createRouteDao() {
        return new JdbcRouteDao(connection);
    }

    @Override
    public StopDao createStopDao() {
        return new JdbcStopDao(connection);
    }

    @Override
    public PlanDao createPlanDao() {
        return new JdbcPlanDao(connection);
    }

    @Override
    public PlanOfStopsDao createPlanOfStopsDao() {
        return new JdbcPlanOfStopsDao(connection);
    }

    @Override
    public RouteStopsDao createRouteStopsDao() {
        return new JdbcRouteStopsDao(connection);
    }
}
