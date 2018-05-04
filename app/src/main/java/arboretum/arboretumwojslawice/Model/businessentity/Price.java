package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Price implements AdapterItem {

    @ColumnInfo(name = "IdPrice")
    private Integer idPrice;

    @ColumnInfo(name = "Amount")
    private Double amount;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Kind")
    private Integer kind;


    public Price(Integer idPrice, Double amount, String type, String description, Integer kind) {
        this.idPrice = idPrice;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.kind = kind;
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

    public String getAmountString() { return String.valueOf(amount.intValue());}

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "(id = " + idPrice + ") " + type + " - " + amount + " zł";
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
