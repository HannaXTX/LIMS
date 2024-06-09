package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection con;
    private final String dbUsername = "root"; // username
    private final String dbPassword = "LIMSDB2024"; // password
    private final String URL = "127.0.0.1"; // server location
    private final String port = "3306"; // port
    private final String dbName = "LIMS"; // database name on MySQL

    public Connector() throws ClassNotFoundException, SQLException {
        connectDB();
    }

    public static Connection getCon() {
        return con;
    }



    private void connectDB() throws ClassNotFoundException, SQLException {
        String dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        try {
            con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("Connection established");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database");
            throw ex;
        }
    }
}
