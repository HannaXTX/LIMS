package entities;

public class Result {

    public enum Status {
        PENDING,
        REJECTED,
        APPROVED
    }

    private String id;
    private String unit;
    private String description;
    private String date;

    public Result(String id, String unit, String description, String date) {
        setId(id);
        setUnit(unit);
        setDescription(description);
        setDate(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
