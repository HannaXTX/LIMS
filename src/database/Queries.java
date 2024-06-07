package database;

import entities.Employee;
import org.w3c.dom.ls.LSOutput;

import javax.management.Query;
import java.sql.*;

public class Queries {

    public String addEmployeeToDB(Employee emp) {
        return "INSERT INTO EMPLOYEE VALUES.........";
    }


    public String UpdateEmployee(Employee emp, String property, String value) {
        return null;
    }

    public static String addEmployeetoDB(String name, String ssn, String address, String dateOfBirth, String major,
                                         String phoneNumber, String email) throws SQLException {
        String query = "INSERT INTO Employees (SSN, Name, Address, DateOfBirth, Major, PhoneNumber, Email) VALUES ('"
                + name + "', '"
                + ssn + "', '"
                + address + "', '"
                + dateOfBirth + "', '"
                + major + "', '"
                + phoneNumber + "', '"
                + email + "')";
        return query;
    }


    public static Statement updateEmployeeInDB(int id, String name, String ssn, String address, String dateOfBirth, String major,
                                               String phoneNumber, String email) throws SQLException {

//        String query = "UPDATE Employees SET Name = '" + name + "', SSN = '" + ssn + "', Address = '" + address + "', " +
//                "DateOfBirth = '" + dateOfBirth + "', Major = '" + major + "', PhoneNumber = '" + phoneNumber + "', " +
//                "Email = '" + email + "' WHERE ID = " + id;
//
//        Statement statement = Connector.getCon().createStatement();

        String query = "UPDATE Employees SET Name = ?, SSN = ?, Address = ?, DateOfBirth = ?, Major = ?, PhoneNumber = ?, Email = ? WHERE ID = ?";

        PreparedStatement statement = Connector.getCon().prepareStatement(query);

        statement.setString(1, name);
        statement.setString(2, ssn);
        statement.setString(3, address);
        statement.setString(4, dateOfBirth);
        statement.setString(5, major);
        statement.setString(6, phoneNumber);
        statement.setString(7, email);
        statement.setInt(8, id);

//        System.out.println(statement);

        System.out.println(statement.executeUpdate());

//        if (statement.execute(query)) {
//            System.out.println(".");
//            statement.executeUpdate(query);
//        }
//        else {
//            System.out.println("error");
//        }

        return statement;


    }

    public static Statement deleteEmployee(Employee emp, int id) throws SQLException {

        String query = "DELETE FROM Employees WHERE ID  = ?";
        PreparedStatement statement = Connector.getCon().prepareStatement(query);
        statement.setInt(1, id);
        System.out.println(statement.executeUpdate());
        return statement;

    }

    public static int getEmployeeCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Employees";

        PreparedStatement stmt = Connector.getCon().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        {

            if (rs.next()) {
                count = rs.getInt(1);
            }


            return count;
        }
    }
}
