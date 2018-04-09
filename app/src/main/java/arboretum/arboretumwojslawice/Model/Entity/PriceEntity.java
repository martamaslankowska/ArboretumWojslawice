package arboretum.arboretumwojslawice.Model.Entity;

/**
 * Created by Komputer on 2018-03-24.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;


@Entity(tableName = "Prices")
//        indices = {@Index(value = {"IdPrice"}, unique = true)})
public class PriceEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdPrice")
    private int idPrice;

    @ColumnInfo(name = "Amount")
    @NonNull
    private double amount;

    public PriceEntity(int idPrice, double amount) {
        this.idPrice = idPrice;
        this.amount = amount;
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

}
