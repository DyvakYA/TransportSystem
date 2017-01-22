package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Dyvak on 17.12.2016.
 */
public abstract class DaoFactory {

    public abstract DaoConnection getConnection();

    public abstract DriverDao createDriverDao(DaoConnection connection);
    public abstract PlanDao createPlanDao(DaoConnection connection);
    public abstract PlanOfStopsDao createPlanOfStopsDao(DaoConnection connection);
    public abstract RouteDao createRouteDao(DaoConnection connection);
    public abstract RouteStopsDao createRouteStopsDao(DaoConnection connection);
    public abstract StopDao createStopDao(DaoConnection connection);
    public abstract TransportDao createTransportDao(DaoConnection connection);
    public abstract UserDao createUserDao(DaoConnection connection);
    public abstract UserDao createUserDao();

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";
    private static  DaoFactory instance;

    public static DaoFactory getInstance(){
        if( instance == null) {
            try {
                InputStream inputStream =
                        DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                instance = (DaoFactory) Class.forName(factoryClass).newInstance();

            } catch (IOException | IllegalAccessException|
                    InstantiationException |ClassNotFoundException e ) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}