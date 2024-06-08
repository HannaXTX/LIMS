package database;

import entities.Customer;
import entities.Employee;
import entities.Sample;
import entities.Test;

import java.sql.*;

public class Queries {

    public String addEmployeeToDB(Employee emp) {
        return "INSERT INTO EMPLOYEE VALUES.........";
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


    public static Statement updateEmployeeInDB(int id, String name, String job,
                                               String phoneNumber, String email) throws SQLException {

//        String query = "UPDATE Employees SET Name = '" + name + "', SSN = '" + ssn + "', Address = '" + address + "', " +
//                "DateOfBirth = '" + dateOfBirth + "', Major = '" + major + "', PhoneNumber = '" + phoneNumber + "', " +
//                "Email = '" + email + "' WHERE ID = " + id;
//
//        Statement statement = Connector.getCon().createStatement();

        String query = "UPDATE CUSTOMER SET Name = ?, PhoneNumber = ?, Email = ?, Job = ? WHERE ID = ?";

        PreparedStatement statement = Connector.getCon().prepareStatement(query);

        statement.setString(1, name);
        statement.setString(2, job);
        statement.setString(3, phoneNumber);
        statement.setString(4, email);
        statement.setInt(5, id);

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


    public static Statement deleteCustomer(Customer cus, int id) throws SQLException {

        String query = "DELETE FROM CUSTOMER WHERE ID  = ?";
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



    public static void updateSampleInDB(String code, String name, String productionDate, String expirationDate, String storage, String temperature, String sampleType) throws SQLException {
        String query = "UPDATE Sample SET Name = ?, ProductionDate = ?, ExpirationDate = ?, Storage = ?, Temperature = ?, IS_A = ? WHERE SCode = ?";

        PreparedStatement statement = Connector.getCon().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, productionDate);
        statement.setString(3, expirationDate);
        statement.setString(4, storage);
        statement.setString(5, temperature);
        statement.setString(6, sampleType);
        statement.setString(7, code);

        statement.executeUpdate();
    }




    public static String addSampleToDB(String code, String name, String productionDate, String expirationDate, String storage, String temperature, String sampleType) throws SQLException {
        String query = "INSERT INTO Sample (SCode, Name, ProductionDate, ExpirationDate, Storage, Temperature, IS_A) VALUES ('"
                + code + "', '"
                + name + "', '"
                + productionDate + "', '"
                + expirationDate + "', '"
                + storage + "', '"
                + temperature + "', '"
                + sampleType + "')";
        return query;
    }


    public static void deleteSample(Sample sample, String code) throws SQLException {
        String query = "DELETE FROM Sample WHERE SCode = ?";
        PreparedStatement statement = Connector.getCon().prepareStatement(query);
        statement.setString(1, code);
        statement.executeUpdate();
    }


    public static int getSampleCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Sample";

        PreparedStatement stmt = Connector.getCon().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }


    public static int getSampleCount(int number) throws SQLException {
        int count = 0;
        String query = "";
        if (number == 0) {
            query = "SELECT COUNT(*) FROM SAMPLE";  //returns all samples in system
        } else {
//            query = "SELECT COUNT(*) FROM SAMPLE WHERE C = " + number;  //returns specific employee Samples
        }


        PreparedStatement stmt = Connector.getCon().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        {
            if (rs.next()) {
                count = rs.getInt(1);
            }


            return count;
        }
    }



    // Method to add a Test to the database
    public static String addTestToDB(int id, String name, String scode, int eid, double price) throws SQLException {
        String query = "INSERT INTO Test (Tid, Name, Scode, EID, Price) VALUES ("
                + id + ", '"
                + name + "', '"
                + scode + "', "
                + eid + ", "
                + price + ")";
        return query;
    }

    // Method to update a Test in the database
    public static void updateTestInDB(int id, String name, String scode, int eid, double price) throws SQLException {
        String query = "UPDATE Test SET Name = ?, Scode = ?, EID = ?, Price = ? WHERE Tid = ?";

        PreparedStatement statement = Connector.getCon().prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, scode);
        statement.setInt(3, eid);
        statement.setDouble(4, price);
        statement.setInt(5, id);

        statement.executeUpdate();
    }

    // Method to delete a Test from the database
    public static void deleteTest(Test test, int id) throws SQLException {
        String query = "DELETE FROM Test WHERE Tid = ?";
        PreparedStatement statement = Connector.getCon().prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    // Method to get the count of Tests in the database
    public static int getTestCount() throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Test";

        PreparedStatement stmt = Connector.getCon().prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }







}
