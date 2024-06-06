package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection con;
    private String dbURL;
    private String dbUsername = "root"; //11 username
    private String dbPassword = "LIMSDB2024"; // password
    private String URL = "127.0.0.1"; // server location
    private String port = "3306"; // port
    private String dbName = "LIMS"; // database name on MySQL


    public Connector() throws ClassNotFoundException, SQLException {
        connectDB();
    }

    public static  Connection getCon(){
        return con;
    }

    public Connector(String username, String password) throws ClassNotFoundException, SQLException {
        if (username.equals(dbUsername) && password.equals(dbPassword))
            connectDB();
        else {
            throw new SQLException("Invalid username or password");
        }


    }

    private void connectDB() throws ClassNotFoundException, SQLException {
        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        try {
            con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("Connection established");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database");
        }
    }

}
