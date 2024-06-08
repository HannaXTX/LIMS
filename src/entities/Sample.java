package entities;

public class Sample {
    private int code;
    private String name;
    private int Cid;
    private String ProductionDate;
    private String ExpirationDate;
    private String Storage;
    private String Temperature;
    private String SampleType;

    public Sample(int code,
                  String name,
                  int cid,
                  String productionDate,
                  String expirationDate,
                  String storage,
                  String temperature,
                  String sampleType) {


        this.code = code;
        this.name = name;
//        Cid = cid;
        ProductionDate = productionDate;
        ExpirationDate = expirationDate;
        Storage = storage;
        Temperature = temperature;
        SampleType = sampleType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public int getCid() {
         return Cid;
     }

     public void setCid(int cid) {
         Cid = cid;
     }

    public String getProductionDate() {
        return ProductionDate;
    }

    public void setProductionDate(String productionDate) {
        ProductionDate = productionDate;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getStorage() {
        return Storage;
    }

    public void setStorage(String storage) {
        Storage = storage;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getSampleType() {
        return SampleType;
    }

    public void setSampleType(String sampleType) {
        SampleType = sampleType;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
//                ", Cid=" + Cid +
                ", ProductionDate='" + ProductionDate + '\'' +
                ", ExpirationDate='" + ExpirationDate + '\'' +
                ", Storage='" + Storage + '\'' +
                ", Temperature='" + Temperature + '\'' +
                ", SampleType='" + SampleType + '\'' +
                '}';
    }


}