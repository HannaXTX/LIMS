package entities;

public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String job;

    public Customer(String id, String name, String phoneNumber, String email, String job) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setJob(job);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
