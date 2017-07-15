package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Connector {

   //private static final String URL = "jdbc:mysql://localhost:3306/mini_orm?createDatabaseIfNotExist=true";

   //private static final String USER = "root";

   //private static final String PASSWORD = "1234";

    private static Connection connection = null;

    private Connector() {}

    public static void initConnection(String driver, String username, String password,
                                      String host, String port, String dbName, String options) throws SQLException {

        Properties connectionProperties = new Properties();

        connectionProperties.setProperty("user", username);
        connectionProperties.setProperty("password", password);
        
        connection = DriverManager.getConnection("jdbc:" + driver + "://" + host +
            ":" + port + "/" + dbName + "?" + options, connectionProperties);
    }

    public static void initConnection(String driver, String username, String password,
                                      String host, String port, String dbName) throws SQLException {

        Properties connectionProperties = new Properties();

        connectionProperties.setProperty("user", username);
        connectionProperties.setProperty("password", password);

        connection = DriverManager.getConnection("jdbc:" + driver + "://" + host +
                ":" + port + "/" + dbName, connectionProperties);
    }

    public static Connection getConnection() {
        return connection;
    }
}
