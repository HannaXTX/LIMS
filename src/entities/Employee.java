package entities;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.AbstractMap;

public class Employee {

    private final SimpleIntegerProperty id;
    private String name;
    private String ssn;
    private String address;
    private String dateOfBirth;
    private String major;
    private String phoneNumber;
    private String email;

    public Employee(int id, String name, String ssn, String address, String dateOfBirth, String major,
                    String phoneNumber, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.name = name;
        this.ssn = ssn;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.major = major;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    static String[] emp = {"ID", "Name", "SSN", "Address", "Date of Birth", "Major", "Phone Number", "Email"};

    public static String[] getCols() {
        return emp;
    }

    public String get(int i) {
        return switch (i) {
            case 1 -> getName();
            case 2 -> getSsn();
            case 3 -> getAddress();
            case 4 -> getDateOfBirth();
            case 5 -> getMajor();
            case 6 -> getPhoneNumber();
            case 7 -> getEmail();
            default -> throw new IndexOutOfBoundsException("Invalid index: " + i);
        };
    }
    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMajor() {
        return major;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static void setEmp(String[] emp) {
        Employee.emp = emp;
    }

}
