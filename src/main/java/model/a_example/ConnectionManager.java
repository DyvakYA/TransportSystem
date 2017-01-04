package model.a_example;

/**
 * Created by Dyvak on 28.12.2016.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

    static Connection con;
    static String url = "jdbc:mysql://localhost/mydb";
    static String username = "root";
    static String password = "root";

    public static Connection getConnection()
    {

            //String url = "jdbc:odbc:" + "DataSource";
            // assuming "DataSource" is your DataSource name

            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            try
            {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
                con = DriverManager.getConnection(url, username, password);

                // assuming your SQL Server's	username is "username"
                // and password is "password"

            }

            catch (SQLException ex)
            {
                ex.printStackTrace();
            }

        return con;
    }
}