package model.dao.jdbc;

import model.dao.BusDao;
import model.dao.DaoFactory;

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
    public BusDao createBusDao() {
        return new JdbcBusDao(connection);
    }
}
