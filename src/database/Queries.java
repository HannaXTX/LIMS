package database;

import entities.Employee;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    public String addEmployeeToDB(Employee emp) {
        return "INSERT INTO EMPLOYEE VALUES.........";
    }

    public String deleteEmployee(Employee emp, String property, String value) {
        return "DELETE FROM EMPLOYEE WHERE '" + property + "' = '" + value + "'";
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
        String query = "UPDATE Employees SET Name = ?, SSN = ?, Address = ?, DateOfBirth = ?, Major = ?, " +
                "PhoneNumber = ?, Email = ? WHERE ID = ?";


        PreparedStatement statement = Connector.getCon().prepareStatement(query);

        statement.setString(1, name);
        statement.setString(2, ssn);
        statement.setString(3, address);
        statement.setString(4, dateOfBirth);
        statement.setString(5, major);
        statement.setString(6, phoneNumber);
        statement.setString(7, email);
        statement.setInt(8, id);
        statement.executeUpdate();
        return statement;


    }
}
