package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "PricesTranslations",
        foreignKeys = @ForeignKey(entity = PriceEntity.class, parentColumns = "IdPrice", childColumns = "IdPrice"))
public class PriceTranslationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IdPrice")
    private int idPrice;

    @ColumnInfo(name = "Type")
    @NonNull
    private String type;


    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }

    public int getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(int idPrice) {
        this.idPrice = idPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}