package entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Result {
    private SimpleIntegerProperty resId;
    private SimpleStringProperty unit;
    private SimpleStringProperty description;
    private SimpleStringProperty date;

    public Result(int resId, String unit, String description, String date) {
        this.resId = new SimpleIntegerProperty(resId);
        this.unit = new SimpleStringProperty(unit);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
    }

    // Getters
    public int getResId() {
        return resId.get();
    }

    public String getUnit() {
        return unit.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getDate() {
        return date.get();
    }

    // Setters
    public void setResId(int resId) {
        this.resId.set(resId);
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    // Property getters for JavaFX bindings
    public SimpleIntegerProperty resIdProperty() {
        return resId;
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }
}
