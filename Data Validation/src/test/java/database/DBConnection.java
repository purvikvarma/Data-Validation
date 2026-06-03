package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static String driverName = "com.mysql.cj.jdbc.Driver";
    static String serverName = "localhost";
    static String databaseName = "ecommerce_learning";
    static String url = "jdbc:mysql://" + serverName + ":3306/" + databaseName;
    static String username = "root";
    static String password = "ipoD5plu$";

    static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public static void disconnectDatabase() {
        try {
            connection.close();
            System.out.println("Database disconnected successfully!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}