package arboretum.arboretumwojslawice.Model.businessentity;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Price {

    private int idPrice;
    private double amount;
    private String type;


    public Price(int idPrice, double amount, String type) {
        this.idPrice = idPrice;
        this.amount = amount;
        this.type = type;
    }

    public int getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(int idPrice) {
        this.idPrice = idPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "(id = " + idPrice + ") " + type + " - " + amount + " zł";
    }
}
