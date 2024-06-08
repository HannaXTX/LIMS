package entities;

import javafx.beans.property.*;

public class Test {
    private final StringProperty id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final StringProperty scode;
    private final IntegerProperty eid;

    public Test(String id, String name, double price, String scode, int eid) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.scode = new SimpleStringProperty(scode);
        this.eid = new SimpleIntegerProperty(eid);
    }

    // ID
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    // Name
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Price
    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    // Scode
    public String getScode() {
        return scode.get();
    }

    public void setScode(String scode) {
        this.scode.set(scode);
    }

    public StringProperty scodeProperty() {
        return scode;
    }

    // EID
    public int getEid() {
        return eid.get();
    }

    public void setEid(int eid) {
        this.eid.set(eid);
    }

    public IntegerProperty eidProperty() {
        return eid;
    }
}
