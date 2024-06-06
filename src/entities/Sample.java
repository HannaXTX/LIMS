package entities;

public class Sample {
    private String code;
    private String name;
    private String receiveDate;
    private String productionDate;
    private String expirationDate;
    private String temperature;
    private String storage;

    public Sample(String code, String name, String receiveDate, String productionDate, String expirationDate,
                  String temperature, String storage) {
        setCode(code);
        setName(name);
        setReceiveDate(receiveDate);
        setProductionDate(productionDate);
        setExpirationDate(expirationDate);
        setTemperature(temperature);
        setStorage(storage);

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}