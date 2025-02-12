package entities;

import javafx.beans.property.*;

public class Test {
    private final SimpleIntegerProperty id;
    private final StringProperty name;
    private final SimpleDoubleProperty price;
    private final SimpleIntegerProperty eid;

    public Test(int id, String name, int eid, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.eid = new SimpleIntegerProperty(eid);
    }

    // ID
    public int getId() {
        return id.get();
    }


    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public SimpleDoubleProperty getPriceProperty() {
        return price;
    }

    public SimpleIntegerProperty getEidProperty() {
        return eid;
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

    public SimpleIntegerProperty idProperty() {
        return id;
    }
}
