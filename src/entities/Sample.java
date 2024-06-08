package entities;

import javafx.beans.property.SimpleIntegerProperty;

public class Sample {

    private final SimpleIntegerProperty code;
    private String name;
    private String productionDate;
    private String expirationDate;
    private String storage;
    private String temperature;
    private String sampleType;

    public Sample(int code, String name, int cid, String productionDate, String expirationDate, String storage, String temperature, String sampleType) {
        this.code = new SimpleIntegerProperty(code);
        this.name = name;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
        this.storage = storage;
        this.temperature = temperature;
        this.sampleType = sampleType;
    }

    static String[] sampleComponents = {"Code", "Name", "Production Date", "Expiration Date", "Storage", "Temperature", "Sample Type"};

    public static String[] getComponents() {
        return sampleComponents;
    }

    public SimpleIntegerProperty codeProperty() {
        return code;
    }

    public String get(int i) {
        return switch (i) {
            case 1 -> getName();
            case 2 -> getProductionDate();
            case 3 -> getExpirationDate();
            case 4 -> getStorage();
            case 5 -> getTemperature();
            case 6 -> getSampleType();
            default -> throw new IndexOutOfBoundsException("Invalid index: " + i);
        };
    }

    public SimpleIntegerProperty getCodeProperty() {
        return code;
    }

    public int getCode() {
        return code.get();
    }

    public String getName() {
        return name;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getStorage() {
        return storage;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public static void setSample(String[] sampleComponents) {
        Sample.sampleComponents = sampleComponents;
    }

    public static String[] getSampleComponents() {
        return sampleComponents;
    }

    public static void setSampleComponents(String[] sampleComponents) {
        Sample.sampleComponents = sampleComponents;
    }
}
