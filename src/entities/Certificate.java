package entities;


// hanna
public class Certificate {
    private String code;
    private double totalPrice;

    public Certificate(String code, double totalPrice) {
        setCode(code);
        setTotalPrice(totalPrice);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
