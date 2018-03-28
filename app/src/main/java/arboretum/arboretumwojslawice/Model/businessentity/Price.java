package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Price {

    @ColumnInfo(name = "IdPrice")
    private Integer idPrice;

    @ColumnInfo(name = "Amount")
    private Double amount;

    @ColumnInfo(name = "Type")
    private String type;


    public Price(Integer idPrice, Double amount, String type) {
        this.idPrice = idPrice;
        this.amount = amount;
        this.type = type;
    }

    public Integer getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(Integer idPrice) {
        this.idPrice = idPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
