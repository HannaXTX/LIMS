package entities;

import javafx.beans.property.SimpleIntegerProperty;

public class Customer {
    private final SimpleIntegerProperty id;
    private String name;
    private String phoneNumber;
    private String email;
    private String job;

    public Customer(int id, String name, String phoneNumber, String email, String job) {
        this.id = new SimpleIntegerProperty(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setJob(job);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
