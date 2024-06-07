package menus.tests;

public class Test {
    private int Tid; // AUTO_INCREMENT,
    private String Name;
    private String SCode;
    private int EID;
    private float Price;

    public Test(int Tid, String Name, String SCode, int EID, float Price) {
        this.Tid = Tid;
        this.Name = Name;
        this.SCode = SCode;
        this.EID = EID;
        this.Price = Price;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String sCode) {
        SCode = sCode;
    }

    public int getEID() {
        return EID;
    }

    public void setEID(int EID) {
        this.EID = EID;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }


}
