package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Dyvak on 17.12.2016.
 */
public abstract class DaoFactory {
    public abstract TransportDao createTransportDao();
    public abstract DriverDao createDriverDao();
    public abstract UserDao createUserDao();
    public abstract RouteDao createRouteDao();
    public abstract StopDao createStopDao();
    public abstract PlanDao createPlanDao();
    public abstract PlanOfStopsDao createPlanOfStopsDao();
    public abstract RouteStopsDao createRouteStopsDao();


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